package com.example.jetgames.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import coil.ImageLoader
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
        // home graph
        addHomeScreen(
            navController = navController,
            imageLoader = imageLoader
        )
        addDetailScreen(navController = navController, imageLoader = imageLoader)
        addScreenshotsScreen(imageLoader = imageLoader)
        // filter graph
        addFilterScreen(navController = navController)
        addPlatformsScreen(navController = navController)
        addGenresScreen(navController = navController)
        addOrdersScreen(navController = navController)
        // favorites graph
        addFavoritesScreen(imageLoader = imageLoader)
    }
}
