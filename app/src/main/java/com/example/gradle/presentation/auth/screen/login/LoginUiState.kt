package com.example.gradle.presentation.auth.screen.login

// UI State Data Class
data class LoginUiState(
    val username: String = "dev2",
    val password: String = "123",
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null
)