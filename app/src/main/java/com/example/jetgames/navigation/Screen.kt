package com.example.jetgames.navigation

import androidx.annotation.DrawableRes

//sealed class Screen(val route: String, val arguments: List<NamedNavArgument>) {
//
//    object DetailScreen : Screen(
//        DETAIL_ROUTE,
//        arguments = listOf(
//            navArgument("detailArgs") {
//                type = DetailsNavType()
//            }
//        )
//    )
//
//    object ScreenshotsScreen :
//        Screen(
//            SCREENSHOTS_ROUTE,
//            arguments = listOf(
//                navArgument("screenshotsArgs") {
//                    type = ScreenshotsNavType()
//                }
//            )
//        )
//
//    object FilterScreen : Screen(FILTER_ROUTE, arguments = emptyList())
//
//    object PlatformsScreen : Screen(PLATFORMS_ROUTE, arguments = emptyList())
//
//    object GenresScreen : Screen(GENRES_ROUTE, arguments = emptyList())
//
//    object OrdersScreen : Screen(ORDERS_ROUTE, arguments = emptyList())
//}


sealed class BottomNavigationItem(
    val route: BottomNavigationRoute,
    val title: String,
    @DrawableRes val icon: Int,
) {

    object HomeScreen : BottomNavigationItem(
        route = HomeRoute,
        title = "Home",
        com.example.jetgames.common.R.drawable.ic_home
    )

    object FavoritesScreen : BottomNavigationItem(
        route = FavoritesRoute,
        title = "Favorites",
        com.example.jetgames.common.R.drawable.ic_star
    )
}
