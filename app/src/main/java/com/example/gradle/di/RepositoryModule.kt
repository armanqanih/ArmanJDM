package com.example.gradle.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
//    @Provides
//    @Singleton
//    fun provideDownloadRepository(db: AppDatabase): DownloadRepository {
//        return DownloadRepositoryImpl(db)
//    }
//
//    @Provides
//    @Singleton
//    fun provideUserRepository(apiService: HomeApiService , authManager: AuthManager): UserRepository {
//        return UserRepositoryImpl(apiService = apiService , authManager = authManager)
//    }
//
//    @Provides
//    @Singleton
//    fun provideAuthRepository(authManager: AuthManager, apiService: AuthApiService): AuthRepository {
//        return AuthRepositoryImpl(
//            apiService=apiService,
//            authManager = authManager
//        )
//    }
}