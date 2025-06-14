package com.example.jetgames.navigation.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.jetgames.filter.ui.FilterScreen
import com.example.jetgames.filter.ui.GenresScreen
import com.example.jetgames.filter.ui.OrdersScreen
import com.example.jetgames.filter.ui.PlatformsScreen
import com.example.jetgames.filter.viewmodel.FilterScreenViewModel
import com.example.jetgames.navigation.FilterRoute
import com.example.jetgames.navigation.GenresRoute
import com.example.jetgames.navigation.OrdersRoute
import com.example.jetgames.navigation.PlatformsRoute
import com.example.jetgames.navigation.util.rememberParentEntry

fun NavGraphBuilder.addFilterScreen(
    navController: NavHostController,
) {
    composable<FilterRoute> { backstackEntry ->
        // Filter Screen
        val parentViewModel: FilterScreenViewModel =
            hiltViewModel(
                backstackEntry.rememberParentEntry(
                    navController = navController
                )
            )
        FilterScreen(
            onFilterItemClick = {
//                val screen = when (it) {
//                    Screen.PlatformsScreen.route -> Screen.PlatformsScreen
//                    Screen.GenresScreen.route -> Screen.GenresScreen
//                    Screen.OrdersScreen.route -> Screen.OrdersScreen
//                    else -> {
//                        throw Exception("invalid route")
//                    }
//                }
//                navController.navigate(route = screen.route)
            },
            viewModel = parentViewModel,
            navigateUp = navController::navigateUp
        )
    }
}

fun NavGraphBuilder.addPlatformsScreen(
    navController: NavController,
) {
    composable<PlatformsRoute> { backstackEntry ->
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

fun NavGraphBuilder.addGenresScreen(
    navController: NavController,
) {
    composable<GenresRoute> { backstackEntry ->
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


fun NavGraphBuilder.addOrdersScreen(
    navController: NavController,
) {
    composable<OrdersRoute> { backstackEntry ->
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
