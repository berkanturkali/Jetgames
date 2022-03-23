package com.example.jetgames.navigation

import androidx.annotation.DrawableRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import com.example.jetgames.core.domain.model.navargs.DetailsNavType
import com.example.jetgames.core.domain.model.navargs.ScreenshotsNavType
import com.example.jetgames.navigation.Routes.DETAIL_ROUTE
import com.example.jetgames.navigation.Routes.FAVORITES_ROUTE
import com.example.jetgames.navigation.Routes.FILTER_ROUTE
import com.example.jetgames.navigation.Routes.GENRES_ROUTE
import com.example.jetgames.navigation.Routes.HOME_ROUTE
import com.example.jetgames.navigation.Routes.ORDERS_ROUTE
import com.example.jetgames.navigation.Routes.PLATFORMS_ROUTE
import com.example.jetgames.navigation.Routes.SCREENSHOTS_ROUTE

sealed class Screen(val route: String, val arguments: List<NamedNavArgument>) {

    object DetailScreen : Screen(DETAIL_ROUTE, arguments = listOf(navArgument("detailArgs") {
        type = DetailsNavType()
    }))

    object ScreenshotsScreen :
        Screen(SCREENSHOTS_ROUTE, arguments = listOf(navArgument("screenshotsArgs") {
            type = ScreenshotsNavType()
        }))

    object FilterScreen : Screen(FILTER_ROUTE, arguments = emptyList())

    object PlatformsScreen : Screen(PLATFORMS_ROUTE, arguments = emptyList())

    object GenresScreen : Screen(GENRES_ROUTE, arguments = emptyList())

    object OrdersScreen : Screen(ORDERS_ROUTE, arguments = emptyList())
}

sealed class BottomNavigationItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int,
) {

    object HomeScreen : BottomNavigationItem(HOME_ROUTE,
        title = "Home",
        com.example.jetgames.common.R.drawable.ic_home)


    object FavoritesScreen : BottomNavigationItem(route = FAVORITES_ROUTE,
        title = "Favorites",
        com.example.jetgames.common.R.drawable.ic_star)
}