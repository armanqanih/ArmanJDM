package com.example.gradle.data.datasource.datastore

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.gradle.data.model.RefreshTokenRequest
import com.example.gradle.domain.repository.AuthRepository
import com.example.gradle.domain.util.ResultState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

import java.time.Instant

class TokenRefresher(
    private val authManager: AuthManager,
    private val authRepository: AuthRepository
) {
    private val refreshLock = Mutex()

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun refreshToken(): Boolean = refreshLock.withLock {
        val currentAccessToken = authManager.getAccessToken().first()
        val currentRefreshToken = authManager.getRefreshToken().first()

        if (currentAccessToken.isNullOrEmpty() || currentRefreshToken.isNullOrEmpty()) {
            authManager.clearSession()
            return false
        }

        when (val result = authRepository.refreshToken(
            RefreshTokenRequest(currentAccessToken, currentRefreshToken)
        )) {
            is ResultState.Success -> {
            result.data?.let { response ->
                // Convert ISO 8601 timestamp to epoch milliseconds
                val expiresAt = runCatching {
                    Instant.parse(response.expiresIn).toEpochMilli()
                }.getOrElse {
                    System.currentTimeMillis() + DEFAULT_TOKEN_EXPIRATION_MS
                }

                authManager.saveAuthSession(
                    accessToken = response.accessToken,
                    refreshToken = response.refreshToken,
                    accessTokenExpiration = expiresAt,
                    refreshTokenExpiration = expiresAt, // If API doesn't provide refresh token expiration
                    userId = authManager.getUserId().first() ?: "",
                    status = true
                )
                true
            } ?: false
        }
            is ResultState.Error -> {
            authManager.clearSession()
            false
        }
        }
    }

    companion object {
        private const val DEFAULT_TOKEN_EXPIRATION_MS = 3600 * 1000L // 1 hour fallback
    }
}