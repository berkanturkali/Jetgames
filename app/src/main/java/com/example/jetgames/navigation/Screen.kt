package com.example.jetgames.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import com.example.jetgames.core.domain.model.navargs.DetailsNavType
import com.example.jetgames.core.domain.model.navargs.ScreenshotsNavType
import com.example.jetgames.navigation.Routes.DETAIL_ROUTE
import com.example.jetgames.navigation.Routes.FILTER_ROUTE
import com.example.jetgames.navigation.Routes.HOME_ROUTE
import com.example.jetgames.navigation.Routes.METACRITIC_FILTER_ROUTE
import com.example.jetgames.navigation.Routes.PLATFORMS_ROUTE
import com.example.jetgames.navigation.Routes.SCREENSHOTS_ROUTE

sealed class Screen(val route: String, val arguments: List<NamedNavArgument>) {
    object HomeScreen : Screen(HOME_ROUTE, arguments = emptyList())

    object DetailScreen : Screen(DETAIL_ROUTE, arguments = listOf(navArgument("detailArgs") {
        type = DetailsNavType()
    }))

    object ScreenshotsScreen :
        Screen(SCREENSHOTS_ROUTE, arguments = listOf(navArgument("screenshotsArgs") {
            type = ScreenshotsNavType()
        }))

    object FilterScreen : Screen(FILTER_ROUTE, arguments = emptyList())

    object MetacriticScreen : Screen(METACRITIC_FILTER_ROUTE, arguments = emptyList())

    object PlatformsScreen : Screen(PLATFORMS_ROUTE, arguments = emptyList())
}