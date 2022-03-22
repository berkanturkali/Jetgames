package com.example.jetgames.navigation.graph

import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import coil.ImageLoader
import com.example.jetgames.core.domain.model.navargs.DetailsArgs
import com.example.jetgames.core.domain.model.navargs.ScreenshotsArgs
import com.example.jetgames.details.ui.DetailScreen
import com.example.jetgames.details.ui.ScreenshotsScreen
import com.example.jetgames.home.ui.Home
import com.example.jetgames.navigation.BottomNavigationItem
import com.example.jetgames.navigation.Routes
import com.example.jetgames.navigation.Routes.FILTER_GRAPH_ROUTE
import com.example.jetgames.navigation.Screen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    imageLoader: ImageLoader,
) {
    navigation(
        startDestination = BottomNavigationItem.HomeScreen.route,
        route = Routes.HOME_GRAPH_ROUTE,
    ) {
        addHomeScreen(navController = navController,
            imageLoader = imageLoader)
        addDetailScreen(navController = navController, imageLoader = imageLoader)
        addScreenshotsScreen(imageLoader = imageLoader)
    }
}

fun NavGraphBuilder.addHomeScreen(
    navController: NavController,
    imageLoader: ImageLoader,
) {
    composable(
        route = BottomNavigationItem.HomeScreen.route,
        enterTransition = {
            slideInVertically(initialOffsetY = { +1000 },
                animationSpec = spring())
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
        //home screen
        Home(imageLoader = imageLoader, navigateToDetailScreen = { id, list ->
                val detailsArgs = DetailsArgs(id, list)
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val args = Uri.encode(moshi.adapter(DetailsArgs::class.java).toJson(detailsArgs))
                navController.navigate("${Screen.DetailScreen.route}/$args")
            },
            navigateToFilterScreen = {
                navController.navigate(FILTER_GRAPH_ROUTE)
            }
        )
    }
}

fun NavGraphBuilder.addDetailScreen(
    navController: NavController,
    imageLoader: ImageLoader,
) {
    composable(
        route = Screen.DetailScreen.route + "/{detailArgs}",
        arguments = Screen.DetailScreen.arguments,
        enterTransition = {
            scaleIn()
        },
        exitTransition = {
            scaleOut()
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
        },
    ) {
        //detail screen
        DetailScreen(imageLoader = imageLoader) { screenshots, page ->
            val screenshotArgs = ScreenshotsArgs(screenshots = screenshots, selectedPage = page)
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val args = Uri.encode(moshi.adapter(ScreenshotsArgs::class.java).toJson(screenshotArgs))
            navController.navigate("${Screen.ScreenshotsScreen.route}/$args")
        }
    }
}

fun NavGraphBuilder.addScreenshotsScreen(
    imageLoader: ImageLoader,
) {
    composable(
        route = Screen.ScreenshotsScreen.route + "/{screenshotsArgs}",
        arguments = Screen.ScreenshotsScreen.arguments
    ) {
        //Screenshots screen
        ScreenshotsScreen(imageLoader = imageLoader)
    }
}
