package com.example.gradle.ui.navigation

sealed class ScreenNavigation(val route: String) {

    object CourseListScreen : ScreenNavigation(route = "CourseList")
    object CourseDetail : ScreenNavigation(route = "CourseDetail")
    object ExploreScreen : ScreenNavigation(route = "Explore")
    object ProfileScreen : ScreenNavigation(route = "Profile_screen")



}