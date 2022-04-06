package com.example.jetgames.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.ImageLoader
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.common.R
import com.example.jetgames.common.components.ErrorItem
import com.example.jetgames.common.components.GameItem
import com.example.jetgames.common.components.LoadingItem
import com.example.jetgames.common.components.ShimmerModifier
import com.example.jetgames.core.domain.model.games.Game
import com.example.jetgames.core.domain.model.games.GameModel
import com.example.jetgames.home.components.GameGalleryItem
import com.example.jetgames.home.components.HomeToolbar
import com.example.jetgames.home.components.SeparatorItem
import com.example.jetgames.home.viewmodel.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun Home(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    imageLoader: ImageLoader,
    navigateToDetailScreen: ((Int, List<String?>?) -> Unit)? = null,
    navigateToFilterScreen: () -> Unit,
) {

    val homeState by viewModel.homeState.collectAsState()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = homeState.isRefreshing)

    val isGalleryMode = homeState.homeViewPreferences.isGalleryMode

    val games: LazyPagingItems<GameModel> = viewModel.games.collectAsLazyPagingItems()

    val listState = rememberLazyListState()

    val scope = rememberCoroutineScope()

    viewModel.setRefresh(games.loadState.refresh is LoadState.Loading)

    DefaultScreenUI(
        toolbar = {
            HomeToolbar(
                filterClick = { navigateToFilterScreen.invoke() },
                galleryListToggleClick = { viewModel.setGalleryMode(!isGalleryMode) },
                viewModel = viewModel,
                prefCount = homeState.filterCount
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = listState.firstVisibleItemIndex > 0,
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            listState.scrollToItem(0)
                        }
                    },
                    modifier = Modifier
                        .padding(
                            dimensionResource(id = R.dimen.dimen_16)
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_up),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    ) {

        SwipeRefresh(state = swipeRefreshState, onRefresh = games::refresh) {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                state = listState
            ) {
                items(games) { gameModel ->
                    when (gameModel) {
                        is GameModel.SeparatorItem -> {
                            SeparatorItem(separator = gameModel.separator)
                        }
                        is GameModel.GameItem -> {
                            val game = gameModel.game
                            if (isGalleryMode) {
                                GameGalleryItem(
                                    modifier = Modifier
                                        .animateItemPlacement(),
                                    game = game,
                                    imageLoader = imageLoader,
                                    onItemClick = navigateToDetailScreen
                                )
                            } else {
                                GameItem(
                                    modifier = Modifier.animateItemPlacement(),
                                    imageLoader = imageLoader,
                                    onItemClick = navigateToDetailScreen,
                                    metaCritic = game.metaCritic,
                                    metacriticColor = game.calculateRgbFromMetacritic(),
                                    rating = game.rating?.toFloat(),
                                    ratingColor = game.calculateRgbFromRating(),
                                    id = game.id!!,
                                    screenShots = game.screenShots,
                                    icon = viewModel.getIcon(game = game),
                                    name = game.name,
                                    released = game.released,
                                    image = game.backgroundImage
                                )
                            }
                        }
                    }
                }

                games.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            if (isGalleryMode) {
                                // Gallery Item
                                items(10) {
                                    if (it % 5 == 0) {
                                        SeparatorItemShimmer()
                                        Divider()
                                    } else {
                                        GameGalleryItemShimmer(imageLoader = imageLoader)
                                    }
                                }
                            } else {
                                items(10) {
                                    if (it % 5 == 0) {
                                        SeparatorItemShimmer()
                                        Divider()
                                    } else {
                                        GameItemShimmer(imageLoader = imageLoader)
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
                                ErrorItem(
                                    modifier = Modifier.fillParentMaxSize(),
                                    message = e.error.localizedMessage
                                        ?: stringResource(id = R.string.something_went_wrong),
                                    onRetryClick = games::retry
                                )
                            }
                        }
                        loadState.append is LoadState.Error -> {
                            val e = games.loadState.append as LoadState.Error
                            item {
                                ErrorItem(
                                    modifier = Modifier
                                        .fillParentMaxWidth()
                                        .wrapContentHeight(),
                                    message = e.error.localizedMessage
                                        ?: stringResource(id = R.string.something_went_wrong),
                                    onRetryClick = games::retry
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SeparatorItemShimmer() {
    SeparatorItem(
        childModifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(30.dp)
            .clip(CircleShape)
            .then(ShimmerModifier),
        separator = ""
    )
}

@Composable
private fun GameItemShimmer(imageLoader: ImageLoader) {
    GameItem(
        childModifier = ShimmerModifier,
        imageLoader = imageLoader,
        isLoading = true,
        id = 1,
        name = "Dummy",
        metaCritic = 95,
        rating = 5f,
        released = "2020-12-24"
    )
}

@Composable
private fun GameGalleryItemShimmer(imageLoader: ImageLoader) {
    GameGalleryItem(
        isLoading = true,
        game = Game(),
        imageLoader = imageLoader,
        childModifier = Modifier
            .height(30.dp)
            .padding(
                vertical = dimensionResource(id = R.dimen.dimen_8),
                horizontal = 10.dp
            )
            .then(ShimmerModifier)
    )
}
