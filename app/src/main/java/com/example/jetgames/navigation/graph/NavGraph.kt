package com.example.jetgames.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.ImageLoader
import com.example.jetgames.navigation.FilterGraphRoute
import com.example.jetgames.navigation.HomeRoute

@Composable
fun JetGamesNavGraph(
    navController: NavHostController,
    imageLoader: ImageLoader,
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
    ) {
        homeScreen(
            navController = navController,
            imageLoader = imageLoader
        )
        detailsScreen(navController = navController, imageLoader = imageLoader)
        screenShotsScreen(imageLoader = imageLoader)

        composable<FilterGraphRoute> {
            FilterNavGraph(navController)
        }


        favoritesScreen(imageLoader = imageLoader)
    }
}
