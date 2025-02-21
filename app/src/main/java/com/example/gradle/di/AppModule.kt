package com.example.gradle.di

import android.app.Application
import android.content.Context
import android.net.http.HttpResponseCache.install
import android.os.Build
import androidx.annotation.RequiresApi

import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import com.example.gradle.data.datasource.datastore.AuthManager
import com.example.gradle.data.datasource.datastore.TokenRefresher
import com.example.gradle.data.datasource.remote.AuthApiService
import com.example.gradle.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging


import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.first

import timber.log.Timber
import java.io.File
import javax.inject.Named

const val headerValue = "G3)1KQh8dW{}5rhWiZtsz_p[K]9!t9wvM!!QOua6Bb;dL@.FX;+xIuInN!Ux{!d,-Gs8(\$Jasz{76fM@XI>)A{{%iat9FyU^;k%mlxkbd8AR(]v8IoFb=3KbP}M5u:@u"
private const val NETWORK_TIME_OUT = 6_000L



@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideKtorClient(
        authManager: AuthManager,
        tokenRefresher: TokenRefresher
    ): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = NETWORK_TIME_OUT
                connectTimeoutMillis = NETWORK_TIME_OUT
                socketTimeoutMillis = NETWORK_TIME_OUT
            }

                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }

                install(HttpTimeout) {
                    requestTimeoutMillis = NETWORK_TIME_OUT
                    connectTimeoutMillis = NETWORK_TIME_OUT
                    socketTimeoutMillis = NETWORK_TIME_OUT
                }

                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Timber.tag("Ktor =>").v(message)
                        }
                    }
                    level = LogLevel.ALL
                }

                install(DefaultRequest) {
                    header("HeaderKey", headerValue)
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }

            install(Auth) {
                bearer {

                    // (A) Load tokens from AuthManager
                    loadTokens {
                        val currentAccessToken = authManager.getAccessToken().first()
                        val currentRefreshToken = authManager.getRefreshToken().first()
                        if (!currentAccessToken.isNullOrBlank()) {
                            BearerTokens(
                                accessToken = currentAccessToken,
                                refreshToken = currentRefreshToken ?: ""
                            )
                        } else {
                            null
                        }
                    }

                    // (B) Refresh tokens automatically on 401
                    refreshTokens {
                        // Attempt to refresh via TokenRefresher
                        val success = tokenRefresher.refreshToken()
                        if (success) {
                            // If refresh was successful, read the newly saved tokens
                            val newAccessToken = authManager.getAccessToken().first() ?: ""
                            val newRefreshToken = authManager.getRefreshToken().first() ?: ""
                            BearerTokens(newAccessToken, newRefreshToken)
                        } else {
                            null
                        }
                    }

                    // (C) Optionally always send token without waiting for 401,
                    //     e.g. if your API domain is "adminapi.aparatparseh.com"
                    sendWithoutRequest { request ->
                        request.url.host.contains("aparatparseh.com")
                    }
                }
            }
                install(HttpRequestRetry) {
                    maxRetries = 1
                    retryIf { request, response ->
                        response.status == HttpStatusCode.Unauthorized
                    }
                    delayMillis { retry -> retry * 1000L }
                }
            }
        }









    @Provides
    @Singleton
    fun provideTokenRefresher(
        authManager: AuthManager,
        authRepository: AuthRepository
    ): TokenRefresher {
        return TokenRefresher(authManager, authRepository)
    }

    @Provides
    @Singleton
    fun provideAuthApiService(
        @Named("AuthClient") client: HttpClient
    ): AuthApiService {
        return AuthApiService(client)
    }

    @Provides
        @Singleton
        fun provideDiskCache(@ApplicationContext context: Context): coil3.disk.DiskCache {
            return coil3.disk.DiskCache.Builder()
                .directory(File(context.cacheDir, "coil_cache"))
                .maxSizeBytes(512L * 1024 * 1024) // 512MB disk cache
                .build()
        }

        @Provides
        @Singleton
        fun provideApplicationContext(@ApplicationContext context: Context): Context {
            return context
        }


        @Provides
        @Singleton
        fun provideMemoryCache(context: Context): MemoryCache {
            return MemoryCache.Builder()
                .maxSizePercent(context = context, 0.5)
                .strongReferencesEnabled(true)
                .build()
        }


        @Provides
        @Singleton
        fun provideImageLoader(
            @ApplicationContext context: Context,
            diskCache: coil3.disk.DiskCache,
            memoryCache: MemoryCache
        ): ImageLoader {
            return ImageLoader.Builder(context)
                .memoryCache { memoryCache }
                .diskCache { diskCache }
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .build()
        }


        @Provides
        @Singleton
        fun provideAuthManager(@ApplicationContext context: Context): AuthManager {
            return AuthManager(context)
        }

    @Provides
    @Singleton
    @Named("AuthClient")
    fun provideAuthHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
            install(DefaultRequest) {
                header("HeaderKey", headerValue)
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }


    }