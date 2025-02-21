package com.example.gradle.domain.repository

import com.example.gradle.data.model.RefreshTokenRequest
import com.example.gradle.data.model.RefreshTokenResponse
import com.example.gradle.domain.model.User
import com.example.gradle.domain.util.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String , deviceId : String): Flow<ResultState<Boolean>>
    suspend fun logout()
    fun getIsLoggedIn(): Flow<Boolean>
    fun getAccessToken(): Flow<String?>
    fun getUser(): Flow<User?>
    suspend fun refreshToken(request: RefreshTokenRequest): ResultState<RefreshTokenResponse>
}
