package com.example.gradle.data.datasource.remote

import com.example.gradle.data.model.RefreshTokenRequest
import com.example.gradle.data.model.RefreshTokenResponse
import com.example.gradle.data.model.auth.AuthRequest
import com.example.gradle.data.model.auth.AuthResponseDto
import com.example.gradle.data.util.NetworkErrorHandler
import com.example.gradle.domain.util.ErrorMessage
import com.example.gradle.domain.util.HttpErrors
import com.example.gradle.domain.util.ResultState



import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

import java.net.URL
import kotlin.coroutines.cancellation.CancellationException


// AuthApiService.kt
class AuthApiService @Inject constructor(
    private val client: HttpClient
) {
    private val url = "https://adminapi.aparatparseh.com/Applogin"
    private val TAG = "NetworkDebug"

    suspend fun login(
        username: String,
        password: String,
        deviceId: String
    ): ResultState<AuthResponseDto> {

        return try {
            val response = client.post(url) {
                contentType(ContentType.Application.Json)
                setBody(AuthRequest(username, password, deviceId))
            }
            val body = response.body<AuthResponseDto>()
            ResultState.Success(body)
        } catch (e: ResponseException) {
            ResultState.Error(NetworkErrorHandler.handle(e))
        } catch (e: JsonConvertException) {
            ResultState.Error(NetworkErrorHandler.handle(e))
        } catch (e: CancellationException) {
            ResultState.Error(ErrorMessage("Request cancelled", HttpErrors.UNDEFINED))
        } catch (e: Exception) {
            ResultState.Error(NetworkErrorHandler.handle(e))
        }
    }


    suspend fun refreshToken(
        accessToken: String,
        refreshToken: String
    ): ResultState<RefreshTokenResponse> {
        val url = URL(url + "refresh-token")
        return try {
            val response = client.post(url) {
                contentType(ContentType.Application.Json)
                setBody(RefreshTokenRequest(accessToken, refreshToken))
            }
            val body = response.body<RefreshTokenResponse>()

            ResultState.Success(body)

        } catch (e: ResponseException) {
            ResultState.Error(NetworkErrorHandler.handle(e))
        } catch (e: JsonConvertException) {
            ResultState.Error(NetworkErrorHandler.handle(e))

        } catch (e: CancellationException) {
            ResultState.Error(ErrorMessage("Request cancelled", HttpErrors.UNDEFINED))

        } catch (e: Exception) {
            ResultState.Error(NetworkErrorHandler.handle(e))
        }
    }
}

