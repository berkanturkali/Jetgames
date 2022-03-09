package com.example.jetgames.filter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.filter.components.*
import timber.log.Timber

@Composable
fun FilterScreen(
    modifier:Modifier = Modifier,
    backstackEntry:NavBackStackEntry? = null
){

    DefaultScreenUI(
        toolbar = {
            FilterToolbar()
        },
        floatingActionButton = {
            ApplyFilterFab()
        }
    ) {
        Column(modifier = modifier.fillMaxSize()) {

            //platform filter
            Platforms(platforms = listOf())

            //genres filter
            Genres()

            //metacritic filter
            Metacritic()
        }
    }
}

@Preview
@Composable
fun FilterScreenPrev() {
    JetgamesTheme {
        FilterScreen()
    }
}