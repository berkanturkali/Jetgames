package com.example.jetgames.filter.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.filter.components.BottomSheetDialogToolbar

@Composable
fun PlatformsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    DefaultScreenUI(toolbar = {
        BottomSheetDialogToolbar(title = "Platforms",
            navController = navController)
    }) {

    }
}