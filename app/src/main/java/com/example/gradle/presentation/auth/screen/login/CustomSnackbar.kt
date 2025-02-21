package com.example.gradle.presentation.auth.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CustomSnackbar(
    title: String,
    message: String,
    errorType: SnackbarErrorType, // Enum for different error types
    onDismiss: () -> Unit
) {
    val backgroundColor = when (errorType) {
        SnackbarErrorType.ERROR -> Color(0xFFFFEBEE) // Light red
        SnackbarErrorType.WARNING -> Color(0xFFFFF3CD) // Light yellow
        SnackbarErrorType.SUCCESS -> Color(0xFFE8F5E9) // Light green
    }

    val borderColor = when (errorType) {
        SnackbarErrorType.ERROR -> Color(0xFFFF1744) // Dark red
        SnackbarErrorType.WARNING -> Color(0xFFFFC107) // Dark yellow
        SnackbarErrorType.SUCCESS -> Color(0xFF2E7D32) // Dark green
    }

    val iconColor = when (errorType) {
        SnackbarErrorType.ERROR -> Color(0xFFD32F2F)
        SnackbarErrorType.WARNING -> Color(0xFFFFA000)
        SnackbarErrorType.SUCCESS -> Color(0xFF388E3C)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = backgroundColor,
            shadowElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .border(2.dp, borderColor, RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.End // Aligns content for RTL text
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Dismiss Icon
                    IconButton(onClick = { onDismiss() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Dismiss",
                            tint = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Status Icon
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Error",
                        tint = iconColor
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Title
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.End
                )

                // Message
                Text(
                    text = message,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End
                )

                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

// Enum for Snackbar Types
enum class SnackbarErrorType {
    ERROR, WARNING, SUCCESS
}

// Preview
@Preview
@Composable
fun PreviewCustomSnackbar() {
    CustomSnackbar(
        title = "لورم ایپسوم متن ساختگی",
        message = "لورم ایپسوم متن ساختگی یا تولید سادگی",
        errorType = SnackbarErrorType.ERROR
    ) {}
}
