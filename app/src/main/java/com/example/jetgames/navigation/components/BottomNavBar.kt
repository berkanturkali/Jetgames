package com.example.jetgames.navigation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetgames.navigation.BottomNavigationItem

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    val items = listOf(
        BottomNavigationItem.HomeScreen,
        BottomNavigationItem.FavoriteGraph,
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    Column {
        Divider()
        BottomNavigation(
            modifier = modifier.height(46.dp),
            backgroundColor = MaterialTheme.colors.primary
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    selectedContentColor = MaterialTheme.colors.onPrimary,
                    unselectedContentColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.3f),
                    icon = {
                        Column(horizontalAlignment = CenterHorizontally) {
                            Icon(painter = painterResource(id = item.icon),
                                contentDescription = item.title)
                            if (selected) {
                                Text(
                                    text = item.title,
                                    textAlign = TextAlign.Center,
                                    fontSize = 10.sp,
                                    color = MaterialTheme.colors.onPrimary
                                )
                            }
                        }
                    },
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true

                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}