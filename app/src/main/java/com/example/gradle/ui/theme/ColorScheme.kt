package org.lotka.android.foundation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.gradle.ui.theme.ColorConstants

// ========================
// Convert Integer Colors to Compose `Color`
// ========================
val LotkaLightColorScheme = LotkaColors(
    primary = Color(ColorConstants.PRIMARY),
    secondary = Color(ColorConstants.SECONDARY),
    background = Color(ColorConstants.BACKGROUND),
    surface = Color(ColorConstants.SURFACE),
    textPrimary = Color(ColorConstants.TEXT_PRIMARY),
    textSecondary = Color(0xFF5D5D5D),
    error = Color(ColorConstants.ERROR),
    success = Color(ColorConstants.SUCCESS),
    warning = Color(ColorConstants.WARNING)
)

val LotkaDarkColorScheme = LotkaColors(
    primary = Color(ColorConstants.DARK_PRIMARY),
    secondary = Color(ColorConstants.SECONDARY),
    background = Color(ColorConstants.DARK_BACKGROUND),
    surface = Color(ColorConstants.DARK_SURFACE),
    textPrimary = Color(ColorConstants.DARK_TEXT_PRIMARY),
    textSecondary = Color(ColorConstants.DARK_TEXT_SECONDARY),
    error = Color(ColorConstants.ERROR),
    success = Color(ColorConstants.SUCCESS),
    warning = Color(ColorConstants.WARNING)
)

// ========================
// Custom Color Class (Only Exposes Necessary Colors)
// ========================
data class LotkaColors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val surface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val error: Color,
    val success: Color,
    val warning: Color
)

// CompositionLocal for Colors
val LocalLotkaColors = staticCompositionLocalOf { LotkaLightColorScheme }
