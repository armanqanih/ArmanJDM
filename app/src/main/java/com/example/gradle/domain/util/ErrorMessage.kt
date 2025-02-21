package com.example.gradle.domain.util

data class ErrorMessage(
    val message: String?,
    val status: HttpErrors,
)
