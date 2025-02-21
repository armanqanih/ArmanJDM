package com.example.gradle.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.LayoutDirection
import com.example.gradle.presentation.util.LocalButtonStyle
import com.example.gradle.presentation.util.provideButtonStyle

import org.lotka.android.foundation.ui.theme.LocalLotkaColors
import org.lotka.android.foundation.ui.theme.LocalLotkaTypography
import org.lotka.android.foundation.ui.theme.LotkaColors
import org.lotka.android.foundation.ui.theme.LotkaDarkColorScheme
import org.lotka.android.foundation.ui.theme.LotkaLightColorScheme
import org.lotka.android.foundation.ui.theme.LotkaTypography
import org.lotka.android.foundation.ui.theme.LotkaTypographySet

@Composable
fun LotkaTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (false) LotkaDarkColorScheme else LotkaLightColorScheme
    val buttonStyles = provideButtonStyle() // ✅ Provide Button Styles

    CompositionLocalProvider(
        LocalLotkaColors provides colors,
        LocalLotkaTypography provides LotkaTypographySet,
        LocalButtonStyle provides buttonStyles, // ✅ Provide button styles globally
        androidx.compose.ui.platform.LocalLayoutDirection provides LayoutDirection.Rtl // Enable RTL
    ) {
        MaterialTheme(
            content = content
        )
    }
}

// ========================
// Expose Only Selected Theme Items
// ========================
object LotkaTheme {
    val color: LotkaColors
        @Composable
        get() = LocalLotkaColors.current

    val typography: LotkaTypography
        @Composable
        get() = LocalLotkaTypography.current
}
