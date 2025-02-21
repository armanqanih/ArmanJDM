package com.example.gradle.ui.navigation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


/**
 * this is a base navigation host that offers:
 *  * navigateBack to go back or close
 *  * navigateBackWithResult to send data back to the previous screen
 *  * AnimatedBuildContent to add default animations
 */
abstract class BaseNavHost(
    protected val navController: NavHostController,
    private val onExitRequest: () -> Unit
) {
    /**
     * implement your composable ui navhost etc in this function
     */
    @Composable
    abstract fun BuildContent()

    /**
     * pops the back stack if possible; otherwise calls onExitRequest
     */
    fun navigateBack() {
        if (navController.previousBackStackEntry == null) {
            onExitRequest()
        } else {
            navController.popBackStack()
        }
    }

    /**
     * passes a result back to the previous back stack entry, then pops
     */
    fun <T> navigateBackWithResult(key: String, result: T) {
        navController.previousBackStackEntry
            ?.savedStateHandle
            ?.set(key, result)
        navController.popBackStack()
    }

    /**
     * displays BuildContent with an animated transition
     * sliding in/out horizontally + fading
     */
    @OptIn(ExperimentalAnimationApi::class)
    @SuppressLint("UnusedContentLambdaTargetStateParameter")
    @Composable
    fun AnimatedBuildContent() {
        val currentBackStackEntry = navController.currentBackStackEntry
        AnimatedContent(
            targetState = currentBackStackEntry?.destination?.route,
            transitionSpec = {
                (slideInHorizontally(
                    initialOffsetX = { +1000 },
                    animationSpec = tween(500)
                ) + fadeIn(animationSpec = tween(500))).togetherWith(
                    slideOutHorizontally(
                        targetOffsetX = { -1000 },
                        animationSpec = tween(500)
                    ) + fadeOut(animationSpec = tween(500))
                )
            },
            label = ""
        ) {
            BuildContent()
        }
    }
}

/**
 * a receiver that listens for results passed back via savedStateHandle
 *
 * @param navBackStackEntry the current nav back stack entry
 * @param navObserveKey the key used for observing navigation result changes
 * @param onStateChanged invoked with the KeyValue whenever a result is posted
 */
//@Composable
//fun HumaNavCallBackReceiver(
//    navBackStackEntry: NavBackStackEntry?,
//    navObserveKey: String,
//    onStateChanged: (keyValue: KeyValue) -> Unit
//) {
//    if (navBackStackEntry != null) {
//        val serializedKeyValue by navBackStackEntry.savedStateHandle
//            .getStateFlow(navObserveKey, "null")
//            .collectAsState()
//
//        LaunchedEffect(serializedKeyValue) {
//            if (serializedKeyValue != "null") {
//                val keyValue = KeyValueHandler.deserialize(serializedKeyValue)
//                onStateChanged(keyValue)
//            }
//        }
//    }
//}

/**
 * an object that simplifies creating a BaseNavHost with a BaseScaffold and
 * a Compose NavHost it automatically calls AnimatedBuildContent for transitions
 */
object MobinNavHost {

    /**
     * creates a BaseNavHost wraps everything in BaseScaffold and sets up a NavHost
     *
     * @param navController the nav controller for navigation
     * @param onFinishActivity invoked if back stack is empty eg exit the activity
     * @param startDestination the typed route or object for your start destination
     * @param content a block where you define your routes/destinations receives
     * a NavGraphBuilder and the BaseNavHost instance for handling back-press or passing results
     */
    @Composable
    fun Build(
        navController: NavHostController,
        onFinishActivity: () -> Unit,
        startDestination: Any,
        bottomNavigationScreens: List<Any>, // Pass list of routes where BottomNav should be shown
        bottomNavigation: @Composable () -> Unit,
        content: NavGraphBuilder.(base: BaseNavHost , paddingValues: PaddingValues) -> Unit
    ) {
        // Observe current route
        val currentBackStackEntry by navController.currentBackStackEntryFlow.collectAsState(initial = null)
        val currentRoute = currentBackStackEntry?.destination?.route

        // Show BottomNav only if currentRoute is in bottomNavigationScreens
        val showBottomNav = bottomNavigationScreens.any { it::class.qualifiedName == currentRoute }

        val hostInstance = object : BaseNavHost(navController, onExitRequest = onFinishActivity) {
            @Composable
            override fun BuildContent() {
                val localHost = this // Capture 'this' in a local variable
                BaseScaffold(
                    bottomBar = if (showBottomNav) bottomNavigation else ({})
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
                    ) {
                        content(localHost , paddingValues)
                    }
                }
            }
        }

        // Display host content with default animations
        hostInstance.BuildContent()
    }
    }

