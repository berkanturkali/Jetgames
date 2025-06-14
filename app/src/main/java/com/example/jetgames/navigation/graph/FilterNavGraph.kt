package com.example.jetgames.navigation.graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

@Composable
fun FilterNavGraph(
    parentNavController: NavController
) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FilterRoute,
    ) {
        filterScreen(navController = navController, parentNavController = parentNavController)
        platformsScreen(navController = navController)
        genresScreen(navController = navController)
        ordersScreen(navController = navController)
    }
}

fun NavGraphBuilder.filterScreen(
    parentNavController: NavController,
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
                val screen = when (it) {
                    PlatformsRoute::class.qualifiedName -> PlatformsRoute
                    GenresRoute::class.qualifiedName -> GenresRoute
                    OrdersRoute::class.qualifiedName -> OrdersRoute
                    else -> {
                        throw Exception("invalid route")
                    }
                }
                navController.navigate(route = screen)
            },
            viewModel = parentViewModel,
            navigateUp = parentNavController::navigateUp
        )
    }
}

fun NavGraphBuilder.platformsScreen(
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

fun NavGraphBuilder.genresScreen(
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


fun NavGraphBuilder.ordersScreen(
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
