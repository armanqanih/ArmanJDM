package com.example.gradle.presentation.auth.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.gradle.ui.app.MainActivity
import com.example.gradle.ui.theme.LotkaTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.gradle.presentation.auth.screen.login.utils.AuthScreen


@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            LotkaTheme {
                AuthScreen(
                    navigateToHome = {
                    MainActivity.getIntent(this).let {
                        this.startActivity(it)
                    }
                    finish()
                }
                )
            }
        }
    }

    companion object {
        private const val PROVIDER_PACKAGE_NAME = "com.google.android.apps.healthdata"

        fun getIntent(
            context: Context,
        ) = Intent(context, AuthActivity::class.java)
    }
}

