package com.example.jetgames.favorites.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.common.components.GameItem
import com.example.jetgames.common.ui.theme.JetgamesTheme
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

    DefaultScreenUI {

        //favorites
        LazyColumn(modifier = modifier) {
            items(favorites) {

                //Games
                GameItem(id = it.id,
                    icon = it.icon,
                    imageLoader = imageLoader,
                    image = it.image,
                    name = it.name,
                    metaCritic = it.metacri,
                    metacriticColor= it.metacri?.calculateRgbFromMetacritic(),
                    rating = it.rating?.toFloat(),
                    ratingColor = it.rating?.calculateRgbFromRating(),
                    released = it.releaseDate)
            }
        }
    }
}