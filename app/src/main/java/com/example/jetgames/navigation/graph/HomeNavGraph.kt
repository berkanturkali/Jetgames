package com.example.jetgames.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.jetgames.navigation.Routes
import com.example.jetgames.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
) {
    navigation(startDestination = Screen.HomeScreen.route, route = Routes.HOME_GRAPH_ROUTE) {
        addHomeScreen(navController = navController)
        addDetailScreen()
    }
}

fun NavGraphBuilder.addHomeScreen(
    navController: NavController
) {
    composable(
        route = Screen.HomeScreen.route
    ) {
        //home screen
    }
}

fun NavGraphBuilder.addDetailScreen(
) {
    composable(
        route = Screen.DetailScreen.route + "/{gameId}",
        arguments = Screen.DetailScreen.arguments
    ) {
        //detail screen
    }
}
