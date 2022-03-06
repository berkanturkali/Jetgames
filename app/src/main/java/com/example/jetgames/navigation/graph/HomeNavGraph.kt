package com.example.jetgames.navigation.graph

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import coil.ImageLoader
import com.example.jetgames.core.domain.model.navargs.DetailsArgs
import com.example.jetgames.core.domain.model.navargs.ScreenshotsArgs
import com.example.jetgames.details.ui.DetailScreen
import com.example.jetgames.details.ui.ScreenshotsScreen
import com.example.jetgames.home.ui.Home
import com.example.jetgames.navigation.Routes
import com.example.jetgames.navigation.Screen
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    imageLoader: ImageLoader,
) {
    navigation(startDestination = Screen.HomeScreen.route, route = Routes.HOME_GRAPH_ROUTE) {
        addHomeScreen(navController = navController, imageLoader = imageLoader)
        addDetailScreen(navController = navController, imageLoader = imageLoader)
        addScreenshotsScreen(imageLoader = imageLoader)
    }
}

fun NavGraphBuilder.addHomeScreen(
    navController: NavController,
    imageLoader: ImageLoader,
) {
    composable(
        route = Screen.HomeScreen.route
    ) {
        //home screen
        Home(imageLoader = imageLoader, navigateToDetailScreen = { id, list ->
            val detailsArgs = DetailsArgs(id, list)
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val args = Uri.encode(moshi.adapter(DetailsArgs::class.java).toJson(detailsArgs))
            navController.navigate("${Screen.DetailScreen.route}/$args")
        })
    }
}

fun NavGraphBuilder.addDetailScreen(
    navController: NavController,
    imageLoader: ImageLoader,

    ) {
    composable(
        route = Screen.DetailScreen.route + "/{detailArgs}",
        arguments = Screen.DetailScreen.arguments
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
