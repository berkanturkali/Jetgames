package com.example.jetgames.navigation.graph

import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import coil.ImageLoader
import com.example.jetgames.details.ui.DetailScreen
import com.example.jetgames.details.ui.ScreenshotsScreen
import com.example.jetgames.details.viewmodel.DetailsViewModel
import com.example.jetgames.details.viewmodel.ScreenshotsViewModel
import com.example.jetgames.home.ui.Home
import com.example.jetgames.navigation.DetailRoute
import com.example.jetgames.navigation.FilterGraphRoute
import com.example.jetgames.navigation.ScreenshotsRoute


fun NavGraphBuilder.homeScreen(
    navController: NavController,
    imageLoader: ImageLoader,
) {
    composable<com.example.jetgames.navigation.HomeRoute>(
        enterTransition = {
            slideInVertically(
                initialOffsetY = { +1000 },
                animationSpec = spring()
            )
        },
        exitTransition = {
            shrinkVertically(shrinkTowards = Alignment.Top)
        },
        popEnterTransition = {
            slideInVertically(initialOffsetY = { 5000 }, animationSpec = tween(700))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
        }
    ) {
        // home screen
        Home(
            imageLoader = imageLoader, navigateToDetailScreen = { id, list ->
                navController.navigate(
                    DetailRoute(
                        id = id,
                        screenshots = list
                    )
                )
            },
            navigateToFilterScreen = {
                navController.navigate(FilterGraphRoute)
            }
        )
    }
}

fun NavGraphBuilder.detailsScreen(
    navController: NavController,
    imageLoader: ImageLoader,
) {
    composable<DetailRoute>(
        enterTransition = {
            scaleIn()
        },
        exitTransition = {
            scaleOut()
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(400))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(700))
        },
    ) {
        // detail screen
        val detailRoute = it.savedStateHandle.toRoute<DetailRoute>()
        val viewModel = hiltViewModel<DetailsViewModel>()
        viewModel.id = detailRoute.id
        viewModel.fetchGame(detailRoute.id)
        viewModel.setScreenshots(detailRoute.screenshots)
        viewModel.checkFavorites(detailRoute.id)
        DetailScreen(
            imageLoader = imageLoader,
            viewModel = viewModel,
            onBackButtonClick = navController::navigateUp
        ) { screenshots, page ->
            navController.navigate(
                ScreenshotsRoute(
                    screenshots = screenshots,
                    selectedPage = page
                )
            )
        }
    }
}

fun NavGraphBuilder.screenShotsScreen(
    imageLoader: ImageLoader,
) {
    composable<ScreenshotsRoute>(
        enterTransition = {
            scaleIn()
        },

        ) {
        // Screenshots screen
        val screenshotsRoute = it.savedStateHandle.toRoute<ScreenshotsRoute>()
        val viewModel = hiltViewModel<ScreenshotsViewModel>()
        viewModel.apply {
            screenShots = screenshotsRoute.screenshots
            selectedPage = screenshotsRoute.selectedPage
        }

        ScreenshotsScreen(imageLoader = imageLoader, viewModel = viewModel)
    }
}
