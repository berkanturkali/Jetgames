package com.example.jetgames.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    val topLevelDestinations = listOf(
        BottomNavigationItem.HomeScreen.route,
        BottomNavigationItem.FavoritesScreen.route
    )

    var isNavbarVisible by mutableStateOf(false)

    fun setIsNavbarVisible(currentRoute: NavDestination?) {
        isNavbarVisible = topLevelDestinations.any {
            val currentRouteString = currentRoute?.route
            currentRouteString == it::class.qualifiedName
        }
    }
}