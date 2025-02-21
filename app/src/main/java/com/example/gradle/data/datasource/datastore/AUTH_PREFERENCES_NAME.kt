package com.example.gradle.data.datasource.datastore

import android.content.Context
import androidx.core.content.edit
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject
import javax.inject.Singleton

private const val AUTH_PREFERENCES_NAME = "encrypted_auth_prefs"

// Extension to get DataStore
val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(AUTH_PREFERENCES_NAME)

@Singleton
class AuthManager @Inject constructor(
    private val context: Context
) {
    private val dataStore: DataStore<Preferences> = context.authDataStore

    companion object Keys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val USER_ID = stringPreferencesKey("user_id")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val ACCESS_TOKEN_EXPIRATION = longPreferencesKey("token_expiration")
        val REFRESH_TOKEN_EXPIRATION = longPreferencesKey("token_expiration")
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val LAST_LOGIN_TIMESTAMP = longPreferencesKey("last_login_timestamp")
        val USER_ROLE = stringPreferencesKey("user_role")
    }

    // Auth state flow
    val authState: Flow<AuthState> = combine(
        getAccessToken(),
        getRefreshToken(),
        getUserId(),
        getIsLoggedIn()
    ) { accessToken, refreshToken, userId, isLoggedIn ->
        if (isLoggedIn && !accessToken.isNullOrEmpty()) {
            AuthState.Authenticated(accessToken, refreshToken, userId)
        } else {
            AuthState.Unauthenticated
        }
    }.distinctUntilChanged()

    suspend fun saveAuthSession(
        accessToken: String,
        refreshToken: String,
        accessTokenExpiration: Long,
        refreshTokenExpiration: Long,
        userId: String,
        status: Boolean,
//        role: String
    ) {
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] = accessToken
            prefs[REFRESH_TOKEN] = refreshToken
            prefs[ACCESS_TOKEN_EXPIRATION] = accessTokenExpiration
            prefs[REFRESH_TOKEN_EXPIRATION] = refreshTokenExpiration
            prefs[USER_ID] = userId
//            prefs[USER_EMAIL] = email
//            prefs[USER_ROLE] = role
            prefs[IS_LOGGED_IN] = status
            prefs[LAST_LOGIN_TIMESTAMP] = System.currentTimeMillis() / 1000 // API 24+ compatible
        }
    }

    suspend fun refreshAccessToken(newToken: String, expiration: Long) {
//        dataStore.edit { prefs ->
//            prefs[ACCESS_TOKEN] = newToken
//            prefs[TOKEN_EXPIRATION] = expiration
//        }
    }

    suspend fun clearSession() {
        dataStore.edit { prefs -> prefs.clear() }
    }

    fun getAccessToken(): Flow<String?> = dataStore.data.map { it[ACCESS_TOKEN] }
    fun getRefreshToken(): Flow<String?> = dataStore.data.map { it[REFRESH_TOKEN] }
    fun getUserId(): Flow<String?> = dataStore.data.map { it[USER_ID] }
    fun getUserEmail(): Flow<String?> = dataStore.data.map { it[USER_EMAIL] }
    fun getIsLoggedIn(): Flow<Boolean> = dataStore.data.map { it[IS_LOGGED_IN] ?: false }
    fun getAccessTokenExpiration(): Flow<Long?> = dataStore.data.map { it[ACCESS_TOKEN_EXPIRATION] }
    fun getRefreshTokenExpiration(): Flow<Long?> = dataStore.data.map { it[REFRESH_TOKEN_EXPIRATION] }
    fun getUserRole(): Flow<String?> = dataStore.data.map { it[USER_ROLE] }

    suspend fun isTokenValid(): Boolean {
        val expirationTime = runBlocking {
            dataStore.data.firstOrNull()?.get(ACCESS_TOKEN_EXPIRATION)
        } ?: return false
        return expirationTime > System.currentTimeMillis() / 1000
    }

    sealed interface AuthState {
        data class Authenticated(
            val accessToken: String,
            val refreshToken: String?,
            val userId: String?
        ) : AuthState

        object Unauthenticated : AuthState
    }
}

// Secure Encrypted DataStore
object EncryptedDataStore {
    private val instance = AtomicReference<DataStore<Preferences>?>(null)

    fun create(context: Context, name: String): DataStore<Preferences> {
        return instance.get() ?: synchronized(this) {
            instance.get() ?: buildEncryptedDataStore(context, name).also { instance.set(it) }
        }
    }

    private fun buildEncryptedDataStore(context: Context, name: String): DataStore<Preferences> {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        val sharedPrefs = EncryptedSharedPreferences.create(
            context,
            name,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        return object : DataStore<Preferences> {
            override val data: Flow<Preferences> = flow {
                emit(sharedPrefs.all.toPreferences())
            }

            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences {
                val currentData = sharedPrefs.all.toPreferences()
                val newData = transform(currentData)
                sharedPrefs.edit {
                    newData.asMap().forEach { (key, value) ->
                        when (value) {
                            is String -> putString(key.name, value)
                            is Int -> putInt(key.name, value)
                            is Boolean -> putBoolean(key.name, value)
                            is Long -> putLong(key.name, value)
                            is Float -> putFloat(key.name, value)
                            else -> error("Unsupported type")
                        }
                    }
                }
                return newData
            }
        }
    }
}

// Extension to convert SharedPreferences to Preferences
private fun Map<String, *>.toPreferences(): Preferences {
    val prefs = mutablePreferencesOf()
    forEach { (key, value) ->
        when (value) {
            is String -> prefs[stringPreferencesKey(key)] = value
            is Int -> prefs[intPreferencesKey(key)] = value
            is Boolean -> prefs[booleanPreferencesKey(key)] = value
            is Long -> prefs[longPreferencesKey(key)] = value
            is Float -> prefs[floatPreferencesKey(key)] = value
        }
    }
    return prefs
}
