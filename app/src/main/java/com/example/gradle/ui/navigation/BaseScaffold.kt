package com.example.gradle.ui.navigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration

/**
 * Enum class to represent the current device configuration.
 * - NORMAL: For default screen size, typically phones in portrait mode.
 * - LANDSCAPE: For devices in landscape orientation.
 * - TABLET: For larger screens like tablets or desktops.
 */

/**
 * BaseScaffold: A versatile scaffold component that adapts to various device configurations.
 * Overloaded versions allow developers to:
 * 1. Provide separate content for NORMAL, LANDSCAPE, and TABLET configurations.
 * 2. Use a single content block with a DeviceConfiguration flag for dynamic handling.
 * 3. Use a simplified version for NORMAL phone layouts only.
 *
 * Common scaffold features like topBar, bottomBar, and floatingActionButton are included.
 */

/**
 * Overload 1: Allows developers to define separate content blocks for NORMAL, LANDSCAPE, and TABLET modes.
 */
@Composable
fun BaseScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    normalContent: @Composable (PaddingValues) -> Unit,
    landscapeContent: @Composable ((PaddingValues) -> Unit)? = null,
    tabletContent: @Composable ((PaddingValues) -> Unit)? = null
) {
    val configuration = LocalConfiguration.current

    // Determine which content to display based on device and orientation
    val selectedContent: @Composable (PaddingValues) -> Unit = when {
        configuration.screenWidthDp >= 600 -> tabletContent ?: normalContent
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE -> landscapeContent ?: normalContent
        else -> normalContent
    }

    Scaffold(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(Color.White)
            .then(modifier),
        topBar = topBar,
        snackbarHost = snackbarHost,
        containerColor = containerColor,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = { paddingValues ->
            selectedContent(paddingValues)
        },
        bottomBar = bottomBar
    )
}

/**
 * Overload 2: Provides a single content block with a DeviceConfiguration flag for dynamic content handling.
 */
@Composable
fun BaseScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = Color.White,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues, DeviceConfiguration) -> Unit
) {
    val configuration = LocalConfiguration.current

    // Determine the current device configuration
    val deviceConfig = when {
        configuration.screenWidthDp >= 600 -> DeviceConfiguration.TABLET
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE -> DeviceConfiguration.LANDSCAPE
        else -> DeviceConfiguration.NORMAL
    }

    Scaffold(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(Color.White)
            .then(modifier),
        topBar = topBar,
        snackbarHost = snackbarHost,
        containerColor = containerColor,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = { paddingValues ->
            content(paddingValues, deviceConfig)
        },
        bottomBar = bottomBar
    )
}

/**
 * Overload 3: Simplified version for NORMAL phone layouts only.
 * Use this when no special handling for landscape or tablet is needed.
 */
@Composable
fun BaseScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = Color.White,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .then(modifier),
        topBar = topBar,
        snackbarHost = snackbarHost,
        containerColor = containerColor,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = { paddingValues ->
            content(paddingValues)
        },
        bottomBar = bottomBar
    )
}
enum class DeviceConfiguration {
    NORMAL,
    LANDSCAPE,
    TABLET
}
