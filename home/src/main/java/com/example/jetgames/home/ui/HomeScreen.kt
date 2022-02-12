package com.example.jetgames.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.common.components.ErrorItem
import com.example.jetgames.common.components.LoadingItem
import com.example.jetgames.core.domain.model.games.Game
import com.example.jetgames.home.components.GameGalleryItem
import com.example.jetgames.home.components.GameItem
import com.example.jetgames.home.components.HomeToolbar
import com.example.jetgames.home.viewmodel.HomeViewModel
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
    imageLoader: ImageLoader,
) {

    val homeState by viewModel.homeState.collectAsState()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = homeState.isRefreshing)

    val isGalleryMode = homeState.isGalleryMode

    val games: LazyPagingItems<Game> = viewModel.games.collectAsLazyPagingItems()

    viewModel.setRefresh(games.loadState.refresh is LoadState.Loading)
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

                games.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            //loading
                            if (isGalleryMode) {
                                //Gallery Item
                            } else {
                                items(10) {
                                    Column() {
                                        GameItem(
                                            childModifier = Modifier
                                                .placeholder(visible = true,
                                                    highlight = PlaceholderHighlight.shimmer(
                                                        White)),
                                            game = Game(name = "Dummy", metaCritic = 85),
                                            imageLoader = imageLoader,
                                            isLoading = true)
                                    }
                                }
                            }
                        }
                        loadState.append is LoadState.Loading -> {
                            item {
                                LoadingItem(modifier = Modifier.fillMaxWidth())
                            }
                        }
                        loadState.refresh is LoadState.Error -> {
                            val e = games.loadState.refresh as LoadState.Error
                            item {
                                ErrorItem(modifier = Modifier.fillParentMaxSize(),
                                    message = e.error.localizedMessage
                                        ?: stringResource(id = com.example.jetgames.common.R.string.something_went_wrong),
                                        onRetryClick = games ::retry)
                            }
                        }
                        loadState.append is LoadState.Error -> {
                            //append error item
                            val e = games.loadState.append as LoadState.Error
                            item {
                                ErrorItem(modifier = Modifier
                                    .fillParentMaxWidth()
                                    .wrapContentHeight(),
                                    message = e.error.localizedMessage
                                        ?: stringResource(id = com.example.jetgames.common.R.string.something_went_wrong),
                                    onRetryClick = games::retry)
                            }
                        }
                    }
                }
            }
        }
    }
}