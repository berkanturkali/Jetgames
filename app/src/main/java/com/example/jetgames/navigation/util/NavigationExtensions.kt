package com.example.jetgames.navigation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

/*
 * https://stackoverflow.com/questions/64955859/scoping-states-in-jetpack-compose/64961032#64961032
 */
@Composable
fun NavBackStackEntry.rememberParentEntry(navController: NavController): NavBackStackEntry {
    val parentId = destination.parent!!.id
    return remember {
        navController.getBackStackEntry(parentId)
    }
}
