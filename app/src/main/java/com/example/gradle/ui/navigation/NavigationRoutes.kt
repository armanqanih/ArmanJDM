package com.example.gradle.ui.navigation

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
sealed interface NavigationRoutes {

    @Serializable
    object Dashboard

    @Serializable
    object QrCode

    @Serializable
    object Support


    @Serializable
    object LogoutScreen

    @Serializable
    object CourseListScreen

    @Serializable
    data class DownloadScreen(
        val id: String
    )




    @Serializable
    data class CourseItemScreen(
        val id : String
    )
    @Serializable
    data class CourseDetailScreen(
        val id: String
    )

    @Serializable
    data class CameraScreen(
        val id: String
    )




}
