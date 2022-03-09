package com.example.jetgames.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.jetgames.filter.ui.FilterScreen
import com.example.jetgames.navigation.Routes
import com.example.jetgames.navigation.Screen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

fun NavGraphBuilder.filterNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = Screen.FilterScreen.route,
        route = Routes.FILTER_GRAPH_ROUTE,
    ) {
        addFilterScreen(navController = navController)
    }
}

fun NavGraphBuilder.addFilterScreen(
    navController: NavHostController,
) {
    composable(
        route = Screen.FilterScreen.route,
    ) { backstackEntry ->
        //Filter Screen
        FilterScreen(backstackEntry = backstackEntry)
    }
}