package com.example.gradle.ui.navigation

sealed class ScreenNavigation(val route: String) {

    object detailScreen : ScreenNavigation(route = "Detail_Screen")
    object favorateScreen : ScreenNavigation(route = "BookMark_Screen")
    object exploreScreen : ScreenNavigation(route = "Home_Screen")
    object searchScreen : ScreenNavigation(route = "Search_Screen")
    object profileScreen : ScreenNavigation(route = "Search_screen")
    object bookMarkScreen : ScreenNavigation(route = "Search_screen")


}