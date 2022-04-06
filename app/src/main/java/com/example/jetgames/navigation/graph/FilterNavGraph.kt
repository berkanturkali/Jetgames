package com.example.jetgames.navigation.graph

import android.annotation.SuppressLint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.jetgames.filter.ui.FilterScreen
import com.example.jetgames.filter.ui.GenresScreen
import com.example.jetgames.filter.ui.OrdersScreen
import com.example.jetgames.filter.ui.PlatformsScreen
import com.example.jetgames.filter.viewmodel.FilterScreenViewModel
import com.example.jetgames.navigation.Routes
import com.example.jetgames.navigation.Screen
import com.example.jetgames.navigation.util.rememberParentEntry
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.material.bottomSheet

fun NavGraphBuilder.filterNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = Screen.FilterScreen.route,
        route = Routes.FILTER_GRAPH_ROUTE,
    ) {
        addFilterScreen(navController = navController)
        addPlatformsScreen(navController = navController)
        addGenresScreen(navController = navController)
        addOrdersScreen(navController = navController)
    }
}

fun NavGraphBuilder.addFilterScreen(
    navController: NavHostController,
) {
    composable(
        route = Screen.FilterScreen.route,
    ) { backstackEntry ->
        // Filter Screen
        val parentViewModel: FilterScreenViewModel =
            hiltViewModel(
                backstackEntry.rememberParentEntry(
                    navController = navController
                )
            )
        FilterScreen(
            onFilterItemClick = {
                val screen = when (it) {
                    Screen.PlatformsScreen.route -> Screen.PlatformsScreen
                    Screen.GenresScreen.route -> Screen.GenresScreen
                    Screen.OrdersScreen.route -> Screen.OrdersScreen
                    else -> {
                        throw Exception("invalid route")
                    }
                }
                navController.navigate(route = screen.route)
            },
            viewModel = parentViewModel,
            navigateUp = navController::navigateUp
        )
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
fun NavGraphBuilder.addPlatformsScreen(
    navController: NavController,
) {
    bottomSheet(
        route = Screen.PlatformsScreen.route
    ) { backstackEntry ->
        // Platforms Screen
        val parentViewModel: FilterScreenViewModel =
            hiltViewModel(
                backstackEntry.rememberParentEntry(
                    navController = navController
                )
            )
        PlatformsScreen(
            items = parentViewModel.filterState.value.selectedPlatforms ?: emptyList(),
            onApplyButtonClick = { platforms ->
                parentViewModel.setPlatforms(platforms)
                navController.navigateUp()
            }
        )
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
fun NavGraphBuilder.addGenresScreen(
    navController: NavController,
) {
    bottomSheet(
        route = Screen.GenresScreen.route
    ) { backstackEntry ->
        val parentViewModel: FilterScreenViewModel =
            hiltViewModel(backstackEntry.rememberParentEntry(navController = navController))

        // Genres Dialog
        GenresScreen(
            items = parentViewModel.filterState.value.selectedGenres ?: emptyList()
        ) {
            parentViewModel.setGenres(it)
            navController.navigateUp()
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
fun NavGraphBuilder.addOrdersScreen(
    navController: NavController,
) {
    bottomSheet(
        route = Screen.OrdersScreen.route
    ) { backstackEntry ->
        val parentViewModel: FilterScreenViewModel =
            hiltViewModel(backstackEntry.rememberParentEntry(navController = navController))
        // Orders Dialog
        OrdersScreen(
            currentOrder = parentViewModel.filterState.value.selectedOrder.order.value
        ) { order ->
            parentViewModel.setSelectedOrder(order)
            navController.navigateUp()
        }
    }
}
