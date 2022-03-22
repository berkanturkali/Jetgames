package com.example.jetgames.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DefaultScreenUI(
    toolbar: (@Composable () -> Unit)? = null,
    floatingActionButton: (@Composable () -> Unit)? = null,
    fabPos: FabPosition = FabPosition.End,
    content: @Composable () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { toolbar?.invoke() },
        floatingActionButton = { floatingActionButton?.invoke() },
        floatingActionButtonPosition = fabPos) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)) {
            content()
        }
    }
}