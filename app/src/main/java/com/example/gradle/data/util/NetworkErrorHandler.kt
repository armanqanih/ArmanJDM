package com.example.gradle.data.util

import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.runBlocking

import org.json.JSONObject

import java.net.SocketTimeoutException

// NetworkErrorHandler.kt
import android.util.Log
import com.example.gradle.domain.util.ErrorMessage
import com.example.gradle.domain.util.HttpErrors
import java.io.IOException

object NetworkErrorHandler {
    private const val TAG = "NetworkDebug"

    fun handle(throwable: Throwable): ErrorMessage {
        Log.d(TAG, "Handling exception: ${throwable.javaClass.simpleName}")
        Log.d(TAG, "Exception message: ${throwable.message}")

        return when (throwable) {
            is ResponseException -> {
                Log.d(TAG, "Identified ResponseException")
                handleHttpException(throwable)
            }
            is JsonConvertException -> {
                Log.d(TAG, "Identified JsonConvertException")
                handleJsonException(throwable)
            }
            is SocketTimeoutException -> {
                Log.d(TAG, "Socket timeout detected")
                ErrorMessage("Connection timeout", HttpErrors.TimeOut)
            }
            is IOException -> {
                Log.d(TAG, "IO Exception detected")
                ErrorMessage("Network unavailable", HttpErrors.NO_INTERNET)
            }
            else -> {
                Log.d(TAG, "Unknown exception type: ${throwable.javaClass}")
                ErrorMessage("Unexpected error", HttpErrors.UNDEFINED)
            }
        }
    }

    private fun handleHttpException(e: ResponseException): ErrorMessage {
        return try {
            Log.d(TAG, "Processing HTTP exception...")
            val response = e.response
            val statusCode = response.status.value
            Log.d(TAG, "HTTP status code: $statusCode")

            val rawBody = runBlocking {
                Log.d(TAG, "Reading response body...")
                response.bodyAsText().also {
                    Log.d(TAG, "Raw response body: $it")
                }
            }

            val extractedMessage = extractErrorMessage(rawBody)
            Log.d(TAG, "Extracted message: $extractedMessage")

            val finalMessage = extractedMessage ?: getDefaultMessage(statusCode).also {
                Log.d(TAG, "Using default message: $it")
            }

            val mappedStatus = mapStatusCode(statusCode).also {
                Log.d(TAG, "Mapped status code $statusCode to $it")
            }

            ErrorMessage(finalMessage, mappedStatus)
        } catch (ex: Exception) {
            Log.e(TAG, "Failed to parse HTTP error", ex)
            ErrorMessage("Failed to parse error response", HttpErrors.UNDEFINED)
        }
    }

    private fun handleJsonException(e: JsonConvertException): ErrorMessage {
        return try {
            Log.d(TAG, "Processing JSON exception...")
            val rawResponse = e.cause?.message?.substringAfter("JSON input: ") ?: "".also {
                Log.d(TAG, "Raw exception message: ${e.cause?.message}")
            }

            Log.d(TAG, "Raw JSON input from exception: $rawResponse")
            val cleanMessage = extractErrorMessage(rawResponse)
            Log.d(TAG, "Cleaned JSON error message: $cleanMessage")

            ErrorMessage(
                message = cleanMessage ?: "Invalid server response format".also {
                    Log.d(TAG, "Using fallback JSON error message")
                },
                status = HttpErrors.BadResponse
            )
        } catch (ex: Exception) {
            Log.e(TAG, "Failed to parse JSON error", ex)
            ErrorMessage("Failed to parse JSON error", HttpErrors.BadResponse)
        }
    }

    private fun extractErrorMessage(rawBody: String): String? {
        Log.d(TAG, "Extracting error from raw body: ${rawBody.take(200)}...")

        return when {
            rawBody.isBlank() -> {
                Log.d(TAG, "Empty raw body")
                null
            }
            rawBody.startsWith("{") -> {
                Log.d(TAG, "Detected JSON object")
                parseJsonError(rawBody)
            }
            rawBody.startsWith("\"") -> {
                Log.d(TAG, "Detected quoted string")
                parseQuotedString(rawBody)
            }
            else -> {
                Log.d(TAG, "Using raw body as message")
                rawBody.takeIf { it.isNotBlank() }
            }
        }
    }

    private fun parseJsonError(jsonString: String): String? {
        return try {
            Log.d(TAG, "Parsing JSON error: ${jsonString.take(200)}...")
            JSONObject(jsonString).let { json ->
                Log.d(TAG, "JSON keys: ${json.keys().asSequence().toList()}")

                listOf("message", "error", "description").firstNotNullOfOrNull {
                    json.optString(it).takeIf { v -> v.isNotBlank() }.also { value ->
                        if (value != null) Log.d(TAG, "Found error key: $it = $value")
                    }
                } ?: run {
                    Log.d(TAG, "No recognized error keys found")
                    null
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to parse JSON error", e)
            null
        }
    }

    private fun parseQuotedString(input: String): String {
        Log.d(TAG, "Original quoted string: $input")
        val cleaned = input
            .trim()
            .removeSurrounding("\"")
            .replace("\\\"", "\"")
        Log.d(TAG, "Cleaned quoted string: $cleaned")
        return cleaned
    }

    private fun mapStatusCode(code: Int): HttpErrors {
        Log.d(TAG, "Mapping status code: $code")
        return when (code) {
            400 -> HttpErrors.BadRequest
            401 -> HttpErrors.Unauthorized
            403 -> HttpErrors.Forbidden
            404 -> HttpErrors.NotFound
            409 -> HttpErrors.Conflict
            500 -> HttpErrors.ServerError
            else -> HttpErrors.BadResponse
        }.also {
            Log.d(TAG, "Mapped $code to $it")
        }
    }

    private fun getDefaultMessage(statusCode: Int): String {
        Log.d(TAG, "Getting default message for status: $statusCode")
        return when (statusCode) {
            401 -> "Authentication failed"
            500 -> "Internal server error"
            else -> "Request failed with status $statusCode"
        }
    }
}