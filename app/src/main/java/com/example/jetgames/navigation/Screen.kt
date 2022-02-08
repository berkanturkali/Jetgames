package com.example.jetgames.navigation

import com.example.jetgames.navigation.Routes.DETAIL_ROUTE
import com.example.jetgames.navigation.Routes.HOME_ROUTE

sealed class Screen(val route: String, val arguments: List<NamedNavArgument>) {
    object HomeScreen : Screen(HOME_ROUTE, arguments = emptyList())

    object DetailScreen : Screen(DETAIL_ROUTE, arguments = listOf(navArgument("gameId") {
        type = NavType.IntType
    }))
}