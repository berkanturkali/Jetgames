package com.example.jetgames.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import com.example.jetgames.common.R
import com.example.jetgames.common.components.RatingTop
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.core.domain.model.games.*
import com.example.jetgames.home.rememberDominantColorState
import com.example.jetgames.home.verticalGradientScrim

@Composable
fun GameGalleryItem(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    game: Game,
    imageLoader: ImageLoader,
    isLoading: Boolean = false,
    onItemClick: ((Int, List<String?>?) -> Unit)? = null,
) {

    val dominantColorState = rememberDominantColorState()
    val backgroundColor by animateColorAsState(targetValue = dominantColorState.color,
        animationSpec = spring(stiffness = Spring.StiffnessLow))
    LaunchedEffect(game.backgroundImage) {
        if (game.backgroundImage != null) {
            dominantColorState.updateColorsFromImageUrl(game.backgroundImage!!)
        } else {
            dominantColorState.reset()
        }
    }

    Card(
        modifier = modifier.padding(8.dp),
        elevation = dimensionResource(id = R.dimen.dimen_8),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.dimen_16)),
        onClick = {
            val screenShots = game.screenShots?.map { it?.image }
            onItemClick?.invoke(game.id!!, screenShots)
        }
    ) {
        Column(
            modifier = Modifier
                .verticalGradientScrim(backgroundColor)
        ) {

            //image
            GameImage(description = game.name,
                image = game.backgroundImage,
                imageLoader = imageLoader,
                isLoading = isLoading
            )

            Row(
                modifier = childModifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //Platforms
                Platforms(platforms = game.parentPlatforms,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp))

                //Metascore
                if (game.metaCritic != null) {
                    MetaCritic(metaCritic = game.metaCritic!!,
                        ratingColor = game.calculateRgbFromMetacritic()!!,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                        childModifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
                        isLoading = isLoading)
                }
            }

            //Name
            val rating = game.ratings?.maxByOrNull {
                it?.percent!!
            }
            Name(name = game.name, icon = rating?.icon, modifier = childModifier)

            //Release Date
            Row(
                modifier = childModifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ReleaseDate(released = game.released)
            }

            //Genres
            Row(
                modifier = childModifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Genres(genres = game.genres, color = backgroundColor)
            }

            //Rating Bar
            Row(
                modifier = childModifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                game.rating_top?.let {
                    RatingTop(rating = it.toDouble())
                }
            }
        }
    }
}

@Preview
@Composable
fun GameGalleryPrev() {
    JetgamesTheme {
        GameGalleryItem(game = Game(
            "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
            esrbRating = EsrbRating(4, "Mature"),
            genres = listOf(Genre(47792, 2, "", "Shooter")),
            id = 3498,
            metaCritic = 93,
            name = "The Witcher 3: Wild Hunt",
            parentPlatforms = listOf(ParentPlatform(Platform(1, "Pc"))),
            rating = 4.67,
            rating_top = 5,
            ratingsCount = 4986,
            released = "12 Dec 2021"
        ), imageLoader = LocalImageLoader.current)

    }
}

