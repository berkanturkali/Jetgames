package com.example.jetgames.details.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.common.R
import com.example.jetgames.common.components.ErrorItem
import com.example.jetgames.common.components.LoadingItem
import com.example.jetgames.core.domain.util.Resource
import com.example.jetgames.details.components.*
import com.example.jetgames.details.viewmodel.DetailsViewModel
import java.text.DecimalFormat

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
    imageLoader: ImageLoader,
    onBackButtonClick: () -> Unit,
    navigateToScreenshots: ((screenshots: List<String?>, page: Int) -> Unit)? = null,
) {

    val screenshots = viewModel.screenShots.observeAsState()
    val game = viewModel.game.observeAsState()

    val scrollState = rememberScrollState()

    val df = DecimalFormat("0.#")

    var isLiked by rememberSaveable {
        mutableStateOf(false)
    }

    var isFavAnimationAlreadyShowed by rememberSaveable {
        mutableStateOf(false)
    }

    val animatedFavIconSize by animateDpAsState(
        targetValue = if (isLiked) 250.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = 450f
        ))

    DefaultScreenUI {

        Column(modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)) {

            when (game.value) {
                is Resource.Success -> {
                    if (game.value?.data != null) {
                        val gameDetail = game.value!!.data
                        //image
                        Box {
                            if (gameDetail?.background_image != null) {
                                DetailScreenImageSection(
                                    imageLoader = imageLoader,
                                    imageUrl = gameDetail.background_image,
                                )
                                if (isLiked && !isFavAnimationAlreadyShowed) {
                                    Icon(
                                        tint = Color.Yellow,
                                        painter = painterResource(id = R.drawable.ic_filled_star),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(animatedFavIconSize)
                                            .align(Center))
                                    if (animatedFavIconSize == 250.dp) {
                                        isFavAnimationAlreadyShowed = true
                                    }
                                }
                            }
                            Toolbar(onBackButtonClick = onBackButtonClick, isLiked = isLiked) {
                                isFavAnimationAlreadyShowed = false
                                isLiked = it

                            }
                        }

                        //name
                        val rating = gameDetail?.ratings?.maxByOrNull {
                            it?.percent!!
                        }
                        if (gameDetail?.name != null) {
                            Name(name = gameDetail.name, icon = rating?.icon)
                        }
                        //Rating & Metascore
                        if (gameDetail?.metacritic != null && gameDetail.rating != null) {
                            RatingMetacriticSection(metacritic = gameDetail.metacritic!!,
                                metacriticColor = gameDetail.calculateRgbFromMetacritic()!!,
                                rating = gameDetail.rating!!,
                                formattedRating = df.format(gameDetail.rating),
                                ratingColor = gameDetail.calculateRgbFromRating()!!)
                        }

                        //Ratingbar
                        if (!gameDetail?.ratings.isNullOrEmpty()) {
                            RatingBar(ratings = gameDetail?.ratings!!)
                        }

                        //Platforms
                        gameDetail?.parent_platforms?.let {
                            if (it.isNotEmpty()) {
                                Platforms(platforms = gameDetail.parent_platforms!!)
                            }
                        }

                        //Screenshots
                        screenshots.value?.let {
                            if (it.isNotEmpty()) {
                                Screenshots(imageLoader = imageLoader,
                                    screenshots = it,
                                    onScreenshotClicked = navigateToScreenshots)
                            }
                        }

                        //Release
                        gameDetail?.released?.let {
                            Released(released = it)
                        }

                        //Genres
                        gameDetail?.genres?.let {
                            if (it.isNotEmpty()) {
                                Genres(genres = it)
                            }
                        }

                        //Description
                        if (gameDetail?.description != null) {
                            Description(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_16),
                                horizontal = dimensionResource(
                                    id = R.dimen.dimen_16)), description = gameDetail.description!!)
                        }
                    }
                }
                is Resource.Error -> {
                    Column(modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center) {
                        ErrorItem(message = game.value?.error!!,
                            modifier = Modifier.fillMaxWidth()) {
                            viewModel.game(viewModel.id)
                        }
                    }
                }
                is Resource.Loading -> {
                    Column(modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center) {
                        LoadingItem(modifier = Modifier
                            .fillMaxWidth()
                            .align(CenterHorizontally))
                    }

                }
                null -> {}
            }
        }
    }
}