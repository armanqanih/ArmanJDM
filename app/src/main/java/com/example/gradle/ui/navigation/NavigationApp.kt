package com.example.gradle.ui.navigation


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gradle.presentation.screen.cours_detail.CourseDetailScreen
import com.example.gradle.presentation.screen.course_list.CourseScreen
import com.example.gradle.presentation.screen.explore.ExploreScreen
import com.example.gradle.presentation.screen.profile.ProfileScreen
import com.example.gradle.ui.app.MainActivity


@Composable
fun NavigationApp(
    activity: MainActivity,
    navController: NavHostController,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    keyboardController: SoftwareKeyboardController,
) {


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {

        },
        content = { paddingValues ->
            val bottomPadding = paddingValues.calculateBottomPadding()
            NavHost(
                navController = navController,
                startDestination = ScreenNavigation.ExploreScreen.route,
                modifier = Modifier.padding(bottom = bottomPadding)
            ) {
                composable(route = ScreenNavigation.ExploreScreen.route) {
                  ExploreScreen()
                }
                composable(route = ScreenNavigation.CourseListScreen.route) {
                  CourseScreen(onNavigateToDetailScreen = {
                      navController.navigate(
                      ScreenNavigation.ExploreScreen.route
                      )
                  })
                }
                composable(route = ScreenNavigation.ProfileScreen.route) {
                  ProfileScreen()
                }
                composable(route = ScreenNavigation.CourseDetail.route) {
                    CourseDetailScreen()
                }


            }
        }
    )
}

