package com.example.gradle.ui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection

import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.currentBackStackEntryAsState

import androidx.navigation.compose.rememberNavController
import com.example.gradle.presentation.composable.StandardScaffold
import com.example.gradle.ui.navigation.NavigationApp
import com.example.gradle.ui.navigation.ScreenNavigation
import com.example.gradle.ui.theme.CleanArchitectureMovieAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val keyboardController = LocalSoftwareKeyboardController.current
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            CleanArchitectureMovieAppTheme {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                StandardScaffold(
                    navController = navController,
                    showBottomBar = currentRoute in listOf(
                        ScreenNavigation.ExploreScreen.route,
                        ScreenNavigation.ProfileScreen.route ,
                        ScreenNavigation.CourseListScreen .route,
                    )  ,
                    modifier = Modifier.fillMaxSize(),

                    ) {

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
        }}
    }
}

