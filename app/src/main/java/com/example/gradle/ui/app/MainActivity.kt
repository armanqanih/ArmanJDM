package com.example.gradle.ui.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels

import androidx.compose.runtime.LaunchedEffect


import androidx.navigation.compose.rememberNavController

import com.example.gradle.ui.navigation.HomeNavHost
import com.example.gradle.ui.theme.LotkaTheme
import com.example.gradle.presentation.auth.activity.AuthActivity
import kotlinx.coroutines.flow.collectLatest


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeViewModel: HomeViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            LaunchedEffect(homeViewModel.isUserLoggedIn) {
                homeViewModel.isUserLoggedIn.collectLatest {isLoggIn->
                    if (isLoggIn == false) {
                        navigateToAuthActivity()
                    }
                }
            }
            val navController = rememberNavController()
            LotkaTheme {
                HomeNavHost(
                    baseNavController = navController,
                    onFinishActivity = { finish() },
                )
            }
        }
    }

    private fun navigateToAuthActivity() {
        AuthActivity.getIntent(this).let {
            this.startActivity(it)
        }
        finish()
    }

    companion object {
        fun getIntent(
            context: Context,
        ) = Intent(context, MainActivity::class.java)
    }

}

