package com.example.gradle.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.gradle.R


data class BottomNavItem(
    @StringRes val title: Int,
    val route: Any,
    @DrawableRes
    val selectedIcon: Int,
    @DrawableRes
    val unSelectedIcon: Int,
    val badgeCount: Int = 0
    )

val bottomNavItems: List<BottomNavItem> = listOf(
    BottomNavItem(
        title = R.string.dashboard, // Pass the resource ID
        route = NavigationRoutes.Dashboard,
        selectedIcon = R.drawable.home,
        unSelectedIcon = R.drawable.home
    ),
    BottomNavItem(
        title = R.string.qr_code, // Define a string resource in `strings.xml`
        route = NavigationRoutes.QrCode,
        selectedIcon = R.drawable.scan,
        unSelectedIcon = R.drawable.scan
    )
    ,
    BottomNavItem(
        title = R.string.support, // Define a string resource in `strings.xml`
        route = NavigationRoutes.Support,
        selectedIcon = R.drawable.call_calling,
        unSelectedIcon = R.drawable.call_calling
    )

    ,
    BottomNavItem(
        title = R.string.profile, // Define a string resource in `strings.xml`
        route = NavigationRoutes.LogoutScreen,
        selectedIcon = R.drawable.logout,
        unSelectedIcon = R.drawable.logout,
    )


)
