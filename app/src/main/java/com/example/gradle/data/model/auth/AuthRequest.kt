package com.example.gradle.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val username: String,
    val password: String,
    val deviceId: String ,
)
