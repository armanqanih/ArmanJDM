package com.example.gradle.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.gradle.ui.theme.LotkaTheme


// ========================
// Define Button Styles
// ========================


// ========================
// Define Button Colors
// ========================
data class ButtonColors(
    val background: Color,
    val text: Color,
    val border: Color? = null,
    val ripple: Color = Color.Black.copy(alpha = 0.1f)
)

// ========================
// Define Button Dimensions
// ========================
data class ButtonDimensions(
    val heightDp: Int,
    val cornerRadiusDp: Int,
    val strokeWidthDp: Int? = null,
    val buttonWidth: ButtonWidth = ButtonWidth.FillMaxWidth
) {
    sealed class ButtonWidth {
        object FillMaxWidth : ButtonWidth()
        data class WrapContent(val paddingHorizontalDp: Int) : ButtonWidth()
    }
}

// ========================
// Define Button Type
// ========================
data class ButtonType(
    val colors: ButtonColors,
    val dimensions: ButtonDimensions,
    val typeStyle: TextStyle
)


// ========================
// Define Button Style Data Class
// ========================
data class ButtonStyle(
    val primary: ButtonType,
    val secondary: ButtonType,
    val tertiary: ButtonType,
    val destructive: ButtonType
)

// ========================
// Define Button Style Provider
// ========================
val LocalButtonStyle = staticCompositionLocalOf<ButtonStyle> {
    error("No ButtonStyle provided. Make sure to wrap your UI inside LotkaTheme.")
}

// ========================
// Provide Button Styles
// ========================
@Composable
fun provideButtonStyle(): ButtonStyle {
    return ButtonStyle(
        primary = provideButtonType(LotkaTheme.color.primary, LotkaTheme.color.textPrimary),
        secondary = provideButtonType(LotkaTheme.color.background, LotkaTheme.color.primary, border = LotkaTheme.color.primary),
        tertiary = provideButtonType(Color.Transparent, LotkaTheme.color.primary),
        destructive = provideButtonType(LotkaTheme.color.error, LotkaTheme.color.textPrimary)
    )
}

// ========================
// Private Function to Create a Button Type
// ========================
@Composable
private fun provideButtonType(
    background: Color,
    text: Color,
    border: Color? = null,
    ripple: Color = Color.Black.copy(alpha = 0.1f)
): ButtonType {
    return ButtonType(
        colors = ButtonColors(
            background = background,
            text = text,
            border = border,
            ripple = ripple
        ),
        dimensions = ButtonDimensions(
            heightDp = 56,
            cornerRadiusDp = 10
        ),
        typeStyle = LotkaTheme.typography.smallText.copy( fontWeight = FontWeight.Normal , fontSize = 14.sp)
    )
}

