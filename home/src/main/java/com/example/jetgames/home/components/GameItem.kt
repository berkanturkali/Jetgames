package com.example.jetgames.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import com.example.jetgames.common.R
import com.example.jetgames.common.components.RatingBar
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.core.domain.model.games.*
import com.example.jetgames.home.gradientBackground
import com.example.jetgames.home.rememberDominantColorState

@Composable
fun GameItem(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    game: Game,
    imageLoader: ImageLoader,
    isLoading: Boolean = false,
    onItemClick: ((Int,List<String?>?)->Unit)?  =null
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
        onClick = {
            val screenshots=  game.screenShots?.map { it?.image }
            onItemClick?.invoke(game.id!!,screenshots)
                  },
        modifier = modifier
            .padding(vertical = dimensionResource(id = R.dimen.dimen_8),
                horizontal = dimensionResource(id = R.dimen.dimen_8)),
        elevation = dimensionResource(id = R.dimen.dimen_8),
        shape = RoundedCornerShape(
            dimensionResource(id = R.dimen.dimen_16))) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .gradientBackground(color = backgroundColor, 45f)
        ) {

            //Metascore
            if (game.metaCritic != null) {
                Column(
                    modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End
                ) {
                    MetaCritic(
                        isLoading = isLoading,
                        metaCritic = game.metaCritic!!,
                        ratingColor = game.calculateRgbFromMetacritic()!!,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 14.sp,
                        childModifier = childModifier.padding(horizontal = 12.dp, vertical = 2.dp))
                }
            }

            //image & name
            Row(modifier = modifier
                .padding(horizontal = dimensionResource(id = R.dimen.dimen_16))
            ) {
                GameImage(
                    modifier = childModifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
                    description = game.name,
                    image = game.backgroundImage,
                    imageLoader = imageLoader
                )

                Spacer(Modifier.width(dimensionResource(id = R.dimen.dimen_16)))

                if (game.name != null) {
                    val rating = game.ratings?.maxByOrNull {
                        it?.percent!!
                    }
                    Name(
                        name = game.name,
                        icon = rating?.icon,
                        size = 18.sp,
                        modifier = childModifier
                            .align(Alignment.CenterVertically),
                    )
                }
            }

            //date & rating
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.dimen_8),
                    horizontal = dimensionResource(id = R.dimen.dimen_8)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                if (game.rating != null) {
                    RatingBar(rating = game.rating!!.toFloat(),
                        modifier = Modifier
                            .height(12.dp)
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_8)),
                        color = Color.Yellow)
                }
                if (game.released != null) {
                    Text(
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_8),
                            vertical = dimensionResource(id = R.dimen.dimen_8)),
                        text = game.released!!,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.h5,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GameItemPrev(
) {
    JetgamesTheme {
        GameItem(game = Game(
            "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
            esrbRating = EsrbRating(4, "Mature"),
            genres = listOf(Genre(47792, 2, "", "Shooter")),
            id = 3498,
            metaCritic = 92,
            name = "The Witcher 3: Wild Hunt",
            parentPlatforms = listOf(ParentPlatform(Platform(1, "Pc"))),
            rating = 4.67,
            rating_top = 5,
            ratingsCount = 4986,
            released = "12 Dec 2021"
        ), imageLoader = LocalImageLoader.current)
    }
}