package com.example.gradle.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.composable




@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun HomeNavHost(
    baseNavController: NavHostController,
    onFinishActivity: () -> Unit,
) {


    MobinNavHost.Build(
        navController = baseNavController,
        startDestination = NavigationRoutes.Dashboard,
        onFinishActivity = onFinishActivity,
        bottomNavigationScreens = bottomNavItems.map { it.route },
        bottomNavigation = { BottomNavigation(baseNavController, bottomNavItems) }
    ) { navController , paddingValues ->


        composable<NavigationRoutes.Dashboard> {



            }}
}
