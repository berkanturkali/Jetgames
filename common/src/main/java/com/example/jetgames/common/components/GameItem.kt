package com.example.jetgames.common.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import com.example.jetgames.common.R
import com.example.jetgames.common.util.gradientBackground
import com.example.jetgames.common.util.rememberDominantColorState
import com.example.jetgames.core.domain.model.games.Screenshot

@Composable
fun GameItem(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    metaCritic: Int? = null,
    image: String? = null,
    screenShots: List<Screenshot?>? = null,
    id: Int,
    ratingColor: Color? = null,
    metacriticColor: Color? = null,
    icon: String? = null,
    name: String? = null,
    rating: Float? = null,
    released: String? = null,
    imageLoader: ImageLoader,
    isLoading: Boolean = false,
    onItemClick: ((Int, List<String?>?) -> Unit)? = null,
) {

    val dominantColorState = rememberDominantColorState()

    val backgroundColor by animateColorAsState(targetValue = dominantColorState.color,
        animationSpec = spring(stiffness = Spring.StiffnessLow))

    LaunchedEffect(image) {
        if (image != null) {
            dominantColorState.updateColorsFromImageUrl(image)
        } else {
            dominantColorState.reset()
        }
    }

    Card(
        onClick = {
            val screenshots = screenShots?.map { it?.image }
            onItemClick?.invoke(id, screenshots)
        },
        modifier = modifier
            .padding(vertical = dimensionResource(id = R.dimen.dimen_8),
                horizontal = dimensionResource(id = R.dimen.dimen_8)),
        elevation = dimensionResource(id = R.dimen.dimen_8),
        shape = RoundedCornerShape(
            dimensionResource(id = R.dimen.dimen_16))) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .gradientBackground(color = backgroundColor, 45f)
        ) {

            //Metascore
            if (metaCritic != null) {
                Column(
                    modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End
                ) {
                    MetaCritic(
                        isLoading = isLoading,
                        metaCritic = metaCritic,
                        ratingColor = metacriticColor,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 14.sp,
                        childModifier = childModifier.padding(horizontal = 12.dp,
                            vertical = 2.dp))
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
                        description = null,
                        image = image,
                        imageLoader = imageLoader
                    )

                Spacer(Modifier.width(dimensionResource(id = R.dimen.dimen_16)))

                if (name != null) {
                    Name(
                        name = name,
                        icon = icon,
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
                if (rating != null) {
                    RatingBar(rating = rating,
                        modifier = Modifier
                            .height(12.dp)
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_8)),
                        color = ratingColor ?: Color.Yellow)
                }
                if (released != null) {
                    Text(
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_8),
                            vertical = dimensionResource(id = R.dimen.dimen_8)),
                        text = released,
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

//@Preview
//@Composable
//fun GameItemPrev(
//) {
//    JetgamesTheme {
//        GameItem(game = Game(
//            "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
//            esrbRating = EsrbRating(4, "Mature"),
//            genres = listOf(Genre(47792, 2, "", "Shooter")),
//            id = 3498,
//            metaCritic = 92,
//            name = "The Witcher 3: Wild Hunt",
//            parentPlatforms = listOf(ParentPlatform(Platform(1, "Pc"))),
//            rating = 4.67,
//            rating_top = 5,
//            ratingsCount = 4986,
//            released = "12 Dec 2021"
//        ), imageLoader = LocalImageLoader.current)
//    }
//}