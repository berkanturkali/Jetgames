package com.example.jetgames.home.ui

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.core.domain.model.games.Game
import com.example.jetgames.home.viewmodel.HomeViewModel

@Composable
fun Home(
    viewModel:HomeViewModel
){

    val games:LazyPagingItems<Game> = viewModel.games.collectAsLazyPagingItems()

    DefaultScreenUI(toolbar ={}) {
        // home screen
    }
}