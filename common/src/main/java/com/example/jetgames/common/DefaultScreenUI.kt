package com.example.jetgames.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DefaultScreenUI(
    toolbar: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, topBar = {toolbar?.invoke()}) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)) {
            content()
        }
    }
}