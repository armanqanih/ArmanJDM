package com.example.gradle.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gradle.presentation.util.ButtonDimensions
import com.example.gradle.presentation.util.ButtonType

@Composable
fun LotkaButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    buttonType: ButtonType,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    startIcon: Painter? = null,
    endIcon: Painter? = null,
    onClick: () -> Unit
) {
    val backgroundColor = if (isEnabled) buttonType.colors.background else buttonType.colors.background.copy(alpha = 0.5f)
    val textColor = if (isEnabled) buttonType.colors.text else buttonType.colors.text.copy(alpha = 0.5f)
    val borderColor = buttonType.colors.border ?: Color.Transparent
    val borderRadius = buttonType.dimensions.cornerRadiusDp.dp

    val isClickable = isEnabled && !isLoading

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(buttonType.dimensions.heightDp.dp)
            .then(
                if (buttonType.dimensions.buttonWidth is ButtonDimensions.ButtonWidth.FillMaxWidth) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier.wrapContentWidth()
                }
            )
            .background(backgroundColor, shape = RoundedCornerShape(borderRadius))
            .border(
                width = buttonType.dimensions.strokeWidthDp?.dp ?: 0.dp,
                color = borderColor,
                shape = RoundedCornerShape(borderRadius)
            )
            .clickable(
                enabled = isClickable,
                onClick = onClick
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = textColor,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                startIcon?.let {
                    Icon(
                        painter = it,
                        contentDescription = "Start Icon for $text button",
                        tint = textColor,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }

                text?.let {
                    Text(
                        text = it,
                        style = buttonType.typeStyle.copy(color = textColor),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f, fill = false)
                    )
                }

                endIcon?.let {
                    Icon(
                        painter = it,
                        contentDescription = "End Icon for $text button",
                        tint = textColor,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}
