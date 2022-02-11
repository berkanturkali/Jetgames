package com.example.jetgames.home.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.core.domain.model.games.Game
import com.example.jetgames.home.components.GameGalleryItem
import com.example.jetgames.home.components.GameItem
import com.example.jetgames.home.components.HomeToolbar
import com.example.jetgames.home.viewmodel.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun Home(
    viewModel: HomeViewModel,
    imageLoader: ImageLoader,
) {

    val homeState by viewModel.homeState.collectAsState()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = homeState.isRefreshing)

    val isGalleryMode = homeState.isGalleryMode

    val games: LazyPagingItems<Game> = viewModel.games.collectAsLazyPagingItems()

    DefaultScreenUI(toolbar = { HomeToolbar() }) {
        // home screen
        SwipeRefresh(state = swipeRefreshState, onRefresh = games::refresh) {

            //games list
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(games.itemCount) { index ->
                    if (isGalleryMode) {
                        GameGalleryItem(game = games[index] ?: return@items,
                            imageLoader = imageLoader)
                    } else {
                        GameItem(game = games[index] ?: return@items, imageLoader = imageLoader)
                    }
                }
            }
        }
    }
}