package com.example.gradle.presentation.auth.screen.login.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.example.gradle.presentation.auth.screen.login.LoginScreen

@Composable
fun AuthScreen(navigateToHome : () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Background()
        LoginScreen(navigateToHome = {navigateToHome()})
    }
}

@Composable
fun getScreenHeight(): Int {
    val configuration = LocalConfiguration.current
    return configuration.screenHeightDp
}