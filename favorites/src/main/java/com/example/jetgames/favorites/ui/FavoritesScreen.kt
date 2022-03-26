package com.example.jetgames.favorites.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissValue
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.common.components.GameItem
import com.example.jetgames.core.utils.calculateRgbFromMetacritic
import com.example.jetgames.core.utils.calculateRgbFromRating
import com.example.jetgames.favorites.viewmodel.FavoritesViewModel

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    val screenState = viewModel.favoritesScreenState.collectAsState()

    val favorites = screenState.value.favorites

    val isEmptyViewVisible = screenState.value.showEmptyView

    DefaultScreenUI {

        if (isEmptyViewVisible) {
            //empty view

        } else {
            //favorites
            LazyColumn(modifier = modifier) {
                items(items = favorites, key = {
                    it.id
                }) {

                    val dismissState = rememberDismissState(
                        confirmStateChange = { dismissValue ->
                            when (dismissValue) {
                                //swipe rtl
                                DismissValue.DismissedToStart -> {
                                    viewModel.removeFromFavorites(it)
                                    true
                                }
                                else -> false
                            }
                        }
                    )

                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier.animateItemPlacement(),
                        background = {}) {

                        //Games
                        GameItem(
                            id = it.id,
                            icon = it.icon,
                            imageLoader = imageLoader,
                            image = it.image,
                            name = it.name,
                            metaCritic = it.metacri,
                            metacriticColor = it.metacri?.calculateRgbFromMetacritic(),
                            rating = it.rating?.toFloat(),
                            ratingColor = it.rating?.calculateRgbFromRating(),
                            released = it.releaseDate)
                    }
                }
            }
        }
    }
}