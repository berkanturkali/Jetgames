package com.example.jetgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.plusAssign
import coil.ImageLoader
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.navigation.BottomNavigationItem
import com.example.jetgames.navigation.Routes
import com.example.jetgames.navigation.Routes.FAVORITES_GRAPH_ROUTE
import com.example.jetgames.navigation.Screen
import com.example.jetgames.navigation.components.BottomNavBar
import com.example.jetgames.navigation.graph.SetupNavGraph
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetgamesTheme {
                val navController = rememberAnimatedNavController()
                val bottomSheetNavigator = rememberBottomSheetNavigator()
                val topLevelDestinations = listOf(
                    BottomNavigationItem.HomeScreen.route,
                    BottomNavigationItem.FavoritesScreen.route
                )
                val isNavbarVisible =
                    navController.currentBackStackEntryAsState().value?.destination?.route in topLevelDestinations

                navController.navigatorProvider += bottomSheetNavigator
                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(visible = isNavbarVisible,
                            enter = expandVertically(),
                            exit = shrinkVertically()) {
                            BottomNavBar(navController = navController)
                        }
                    }
                ) { padding ->
                    /*
                     * https://stackoverflow.com/a/66574166/11943929
                     */
                    Box(modifier = Modifier
                        .padding(padding)) {
                        SetupNavGraph(navController = navController,
                            bottomSheetNavigator = bottomSheetNavigator,
                            imageLoader = imageLoader)
                    }
                }
            }
        }
    }
}