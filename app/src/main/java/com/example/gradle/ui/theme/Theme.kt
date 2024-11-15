package com.example.gradle.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import org.movie.arm.presentation.theme.Black
import org.movie.arm.presentation.theme.LightGray
import org.movie.arm.presentation.theme.MediumGray
import org.movie.arm.presentation.theme.Red
import org.movie.arm.presentation.theme.White
import org.movie.arm.presentation.theme.gray


private val DarkColorPalette = darkColors(
    primary = Red,
    background = gray,
    onBackground = LightGray,
    onPrimary = White,
    surface = MediumGray,
    onSurface = Black,
)



@Composable
fun CleanArchitectureMovieAppTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}