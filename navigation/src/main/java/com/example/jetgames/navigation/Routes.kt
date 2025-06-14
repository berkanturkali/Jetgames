package com.example.jetgames.navigation

import kotlinx.serialization.Serializable

interface BottomNavigationRoute

@Serializable
data object HomeRoute : BottomNavigationRoute

@Serializable
data class DetailRoute(
    val id: Int,
    val screenshots: List<String?>? = null,
)

@Serializable
data class ScreenshotsRoute(
    val screenshots: List<String?>,
    val selectedPage: Int = 0
)

@Serializable
data object FilterGraphRoute

@Serializable
data object FilterRoute

@Serializable
data object PlatformsRoute

@Serializable
data object GenresRoute

@Serializable
data object OrdersRoute

@Serializable
data object FavoritesRoute : BottomNavigationRoute



