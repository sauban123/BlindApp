package com.coding.meet.blindaiassistant.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coding.meet.blindaiassistant.ui.common.LoadingDialog
import com.coding.meet.blindaiassistant.ui.screens.camera.CameraScreen
import com.coding.meet.blindaiassistant.ui.screens.main.MainScreen
import com.coding.meet.blindaiassistant.ui.screens.result.ResultScreen
import com.coding.meet.blindaiassistant.util.FadeIn
import com.coding.meet.blindaiassistant.util.FadeOut
import com.coding.meet.blindaiassistant.viewmodels.MainViewModel
import com.coding.meet.blindaiassistant.viewmodels.ToolViewModel

/**
 * Created 28-02-2024 at 03:04 pm
 */
val LocalNavControllerProvider: ProvidableCompositionLocal<NavHostController> = staticCompositionLocalOf {
    error("No StringResources provided")
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val mainViewModel = viewModel<MainViewModel>()
    val toolViewModel = viewModel<ToolViewModel>()
    val isLoading by mainViewModel.isLoadingStateFlow.collectAsState()
    LoadingDialog(isLoading)
    CompositionLocalProvider(LocalNavControllerProvider provides navController) {
        NavHost(
            navController = navController,
            route = "ScreenGraph",
            startDestination = RouteScreen.Home.route,
            enterTransition = { FadeIn },
            exitTransition = { FadeOut },
        ) {
            composable(route = RouteScreen.Home.route) {
                MainScreen(mainViewModel = mainViewModel)
            }
            composable(route = RouteScreen.Camera.route) {
                CameraScreen(mainViewModel = mainViewModel)
            }
            composable(route = RouteScreen.Result.route) {
                ResultScreen(mainViewModel = mainViewModel, toolViewModel = toolViewModel)
            }
        }
    }
}
