package com.example.gradle.ui.theme

import androidx.annotation.ColorInt

// ========================
// Define Colors as Integer Values (Hex Codes)
// ========================
object ColorConstants {
    @ColorInt val PRIMARY = 0xFF2A628F.toInt() // Main Brand Color
    @ColorInt val SECONDARY = 0xFF1976D2.toInt() // Secondary Brand Color
    @ColorInt val BACKGROUND = 0xFFEDF1F3.toInt() // App Background (Light Mode)
    @ColorInt val SURFACE = 0xFFF5F5F5.toInt() // Cards, Containers
    @ColorInt val TEXT_PRIMARY = 0xFFFFFFFF.toInt() // Main Text
    @ColorInt val TEXT_SECONDARY = 0xFF757575.toInt() // Secondary Text
    @ColorInt val ERROR = 0xFFDE574C.toInt() // Errors & Warnings
    @ColorInt val SUCCESS = 0xFF388E3C.toInt() // Success Messages
    @ColorInt val WARNING = 0xFFF57C00.toInt() // Warnings

    // Dark Mode Colors
    @ColorInt val DARK_PRIMARY = 0xFF90CAF9.toInt()
    @ColorInt val DARK_BACKGROUND = 0xFF121212.toInt()
    @ColorInt val DARK_SURFACE = 0xFF1E1E1E.toInt()
    @ColorInt val DARK_TEXT_PRIMARY = 0xFFFFFFFF.toInt()
    @ColorInt val DARK_TEXT_SECONDARY = 0xFFB0BEC5.toInt()
}
