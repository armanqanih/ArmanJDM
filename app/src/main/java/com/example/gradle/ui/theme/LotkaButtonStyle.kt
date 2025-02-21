package com.example.gradle.ui.theme

import androidx.compose.runtime.Composable
import com.example.gradle.presentation.util.ButtonType
import com.example.gradle.presentation.util.LocalButtonStyle

object LotkaButtonStyle {
    val primary: ButtonType
        @Composable
        get() = LocalButtonStyle.current.primary

    val secondary: ButtonType
        @Composable
        get() = LocalButtonStyle.current.secondary

    val tertiary: ButtonType
        @Composable
        get() = LocalButtonStyle.current.tertiary

    val destructive: ButtonType
        @Composable
        get() = LocalButtonStyle.current.destructive
}
