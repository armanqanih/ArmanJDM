package com.example.gradle.ui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.ui.platform.LocalSoftwareKeyboardController

import androidx.navigation.compose.rememberNavController
import com.example.gradle.ui.navigation.NavigationApp
import com.example.gradle.ui.theme.CleanArchitectureMovieAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val keyboardController = LocalSoftwareKeyboardController.current

            CleanArchitectureMovieAppTheme {

                    if (keyboardController != null) {
                        NavigationApp(
                            activity = this@MainActivity,
                            navController = navController,
                            isDarkTheme = false,
                            onToggleTheme = { },
                            keyboardController = keyboardController,

                            )
                    }

            }
        }
    }
}

