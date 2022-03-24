package com.example.jetgames.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import coil.ImageLoader
import com.example.jetgames.favorites.ui.FavoritesScreen
import com.example.jetgames.navigation.BottomNavigationItem
import com.example.jetgames.navigation.Routes
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

fun NavGraphBuilder.favoritesNavGraph(
    navController: NavHostController,
    imageLoader: ImageLoader,
) {
    navigation(
        startDestination = BottomNavigationItem.FavoritesScreen.route,
        route = Routes.FAVORITES_GRAPH_ROUTE
    ) {
        addFavoritesScreen(navController = navController, imageLoader = imageLoader)
    }
}

fun NavGraphBuilder.addFavoritesScreen(
    navController: NavHostController,
    imageLoader: ImageLoader,
) {
    composable(
        route = BottomNavigationItem.FavoritesScreen.route
    ) {
        //Favorites Screen
        FavoritesScreen(
            imageLoader = imageLoader
        )
    }
}