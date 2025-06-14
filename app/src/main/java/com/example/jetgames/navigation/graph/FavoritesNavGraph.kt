package com.example.jetgames.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.ImageLoader
import com.example.jetgames.favorites.ui.FavoritesScreen
import com.example.jetgames.navigation.FavoritesRoute

fun NavGraphBuilder.favoritesScreen(
    imageLoader: ImageLoader,
) {
    composable<FavoritesRoute> {
        // Favorites Screen
        FavoritesScreen(
            imageLoader = imageLoader
        )
    }
}
