package com.example.jetgames.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DefaultScreenUI(
    toolbar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    fabPos: FabPosition = FabPosition.End,
    state: ScaffoldState = rememberScaffoldState(),
    content: @Composable () -> Unit,
) {
    Scaffold(
        scaffoldState = state,
        topBar = toolbar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = fabPos,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
        ) {
            content()
        }
    }
}
