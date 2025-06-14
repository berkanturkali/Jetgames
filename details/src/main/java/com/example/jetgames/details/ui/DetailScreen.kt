package com.example.jetgames.details.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.common.R
import com.example.jetgames.common.components.ErrorItem
import com.example.jetgames.common.components.LoadingItem
import com.example.jetgames.core.domain.model.favorites.Favorite
import com.example.jetgames.core.domain.util.Resource
import com.example.jetgames.details.components.*
import com.example.jetgames.details.viewmodel.DetailsViewModel
import java.text.DecimalFormat

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel,
    imageLoader: ImageLoader,
    onBackButtonClick: () -> Unit,
    navigateToScreenshots: ((screenshots: List<String?>, page: Int) -> Unit)? = null,
) {

    val screenState = viewModel.detailsScreenState.collectAsState()

    val game = screenState.value.game

    val screenshots = screenState.value.screenShots

    val isLiked = screenState.value.isLiked

    val scrollState = rememberScrollState()

    val df = DecimalFormat("0.#")

    DefaultScreenUI {

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            when (game) {
                is Resource.Success -> {
                    if (game.data != null) {
                        val gameDetail = game.data
                        // image
                        Box {
                            if (gameDetail?.background_image != null) {
                                DetailScreenImageSection(
                                    imageLoader = imageLoader,
                                    imageUrl = gameDetail.background_image,
                                )
                            }
                            Toolbar(onBackButtonClick = onBackButtonClick, isLiked = isLiked) {
                                gameDetail?.let { game ->
                                    val favorite = Favorite(
                                        id = game.id!!,
                                        name = game.name!!,
                                        rating = game.rating,
                                        image = game.background_image,
                                        metacri = game.metacritic,
                                        releaseDate = game.released,
                                        icon = viewModel.getIcon(game)
                                    )
                                    if (it) {
                                        viewModel.addToFavorites(favorite = favorite)
                                    } else {
                                        viewModel.removeFromFavorites(favorite = favorite)
                                    }
                                }
                            }
                        }

                        // name
                        if (gameDetail?.name != null) {
                            Name(name = gameDetail.name, icon = viewModel.getIcon(game = gameDetail))
                        }
                        // Rating & Metascore
                        if (gameDetail?.metacritic != null && gameDetail.rating != null) {
                            RatingMetacriticSection(
                                metacritic = gameDetail.metacritic!!,
                                metacriticColor = gameDetail.calculateRgbFromMetacritic()!!,
                                rating = gameDetail.rating!!,
                                formattedRating = df.format(gameDetail.rating),
                                ratingColor = gameDetail.calculateRgbFromRating()!!
                            )
                        }

                        // Ratingbar
                        if (!gameDetail?.ratings.isNullOrEmpty()) {
                            RatingBar(ratings = gameDetail?.ratings!!)
                        }

                        // Platforms
                        gameDetail?.parent_platforms?.let {
                            if (it.isNotEmpty()) {
                                Platforms(platforms = gameDetail.parent_platforms!!)
                            }
                        }

                        // Screenshots
                        if (screenshots.isNotEmpty()) {
                            Screenshots(
                                imageLoader = imageLoader,
                                screenshots = screenshots,
                                onScreenshotClicked = navigateToScreenshots
                            )
                        }

                        // Release
                        gameDetail?.released?.let {
                            Released(released = it)
                        }

                        // Genres
                        gameDetail?.genres?.let {
                            if (it.isNotEmpty()) {
                                Genres(genres = it)
                            }
                        }

                        // Description
                        if (gameDetail?.description != null) {
                            Description(
                                modifier = Modifier.padding(
                                    vertical = dimensionResource(id = R.dimen.dimen_16),
                                    horizontal = dimensionResource(
                                        id = R.dimen.dimen_16
                                    )
                                ),
                                description = gameDetail.description!!
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        ErrorItem(
                            message = game.error!!,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            viewModel.fetchGame(viewModel.id)
                        }
                    }
                }
                is Resource.Loading -> {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        LoadingItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(CenterHorizontally)
                        )
                    }
                }
                null -> {}
            }
        }
    }
}
