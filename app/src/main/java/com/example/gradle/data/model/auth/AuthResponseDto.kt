package com.example.gradle.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val status: Boolean,
    val userID: String,
    val access_token :String,
    val refresh_token: String,
    val access_token_expires_at : String,
    val refresh_token_expires_at : String
)
