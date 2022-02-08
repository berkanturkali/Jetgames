package com.example.jetgames.navigation.graph

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.jetgames.common.R
import com.example.jetgames.navigation.Routes
import com.example.jetgames.navigation.Routes.ROOT_GRAPH_ROUTE
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ModalBottomSheetLayout

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    bottomSheetNavigator: BottomSheetNavigator,
) {
    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetElevation = dimensionResource(id = R.dimen.dimen_16),
        sheetShape = RoundedCornerShape(dimensionResource(id = R.dimen.dimen_16))) {
        NavHost(
            navController = navController,
            startDestination = Routes.HOME_GRAPH_ROUTE,
            route = ROOT_GRAPH_ROUTE
        ) {
            //home graph
        }
    }
}
