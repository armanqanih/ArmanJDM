package com.example.gradle.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun Float.toDp(): Dp {
    val density = LocalDensity.current.density
    return (this * density).dp
}



@Composable
fun Dp.toFloat(): Float {
    val density = LocalDensity.current.density
    return this.value / density
}


