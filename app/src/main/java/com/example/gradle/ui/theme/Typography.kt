package org.lotka.android.foundation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.gradle.R


// ========================
// Define Font Family
// ========================
val LotkaFontFamily = FontFamily(
    Font(R.font.quicksand_light, FontWeight.Normal),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_bold, FontWeight.Bold),
    Font(R.font.quicksand_bold, FontWeight.ExtraBold),
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_semibold, FontWeight.ExtraLight)
)

// ========================
// Define Custom Typography Class
// ========================
data class LotkaTypography(
    val largeTitle: TextStyle,  // ExtraBold (Main Page Titles)
    val sectionTitle: TextStyle, // Bold (Section Titles)
    val contentText: TextStyle, // Normal (Main Body Text)
    val secondaryText: TextStyle, // Medium (Supporting Text)
    val hintText: TextStyle,  // Light (Hints, Less Important Text)
    val smallText: TextStyle,  // UltraLight (Captions, Footnotes)
    val ultraSmallText: TextStyle,  // UltraLight (Captions, Footnotes)
    val titleText: TextStyle   ,
    val descriptionText: TextStyle ,
    val buttonText: TextStyle ,
)

// Typography Set with Clear Naming
val LotkaTypographySet = LotkaTypography(
    largeTitle = TextStyle(
        fontFamily = LotkaFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp
    ),
    sectionTitle = TextStyle(
        fontFamily = LotkaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    contentText = TextStyle(
        fontFamily = LotkaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    secondaryText = TextStyle(
        fontFamily = LotkaFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),
    hintText = TextStyle(
        fontFamily = LotkaFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp
    ),
    smallText = TextStyle(
        fontFamily = LotkaFontFamily,
        fontWeight = FontWeight.ExtraLight,
        fontSize = 18.sp
    ),
    ultraSmallText = TextStyle(
        fontFamily = LotkaFontFamily,
        fontWeight = FontWeight.ExtraLight,
        fontSize = 14.sp
    ) ,
    titleText = TextStyle(
        fontFamily = LotkaFontFamily,
        fontWeight = FontWeight.ExtraLight,
        fontSize = 10.sp
    ),
    descriptionText = TextStyle(
        fontFamily = LotkaFontFamily,
        fontWeight = FontWeight.ExtraLight,
        fontSize = 5.sp
    )
            ,
    buttonText = TextStyle(
        fontFamily = LotkaFontFamily,
        fontWeight = FontWeight.ExtraLight,
        fontSize = 7.sp
    )
)

// CompositionLocal for Typography
val LocalLotkaTypography = staticCompositionLocalOf { LotkaTypographySet }
