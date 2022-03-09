package com.example.jetgames.filter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.filter.components.FilterToolbar
import com.example.jetgames.filter.components.Genres
import com.example.jetgames.filter.components.Metacritic
import com.example.jetgames.filter.components.Platforms

@Composable
fun FilterScreen(
    modifier:Modifier = Modifier
){

    DefaultScreenUI(
        toolbar = {
            FilterToolbar()
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