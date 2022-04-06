package com.example.jetgames.common.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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

    val backgroundColor by animateColorAsState(
        targetValue = dominantColorState.color,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )

    LaunchedEffect(image) {
        if (image != null) {
            dominantColorState.updateColorsFromImageUrl(image)
        } else {
            dominantColorState.reset()
        }
    }

    val animatedProgress = remember { Animatable(initialValue = 300f) }
    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = 0f,
            animationSpec = tween(300, easing = FastOutSlowInEasing)
        )
    }
    val animatedModifier = modifier
        .graphicsLayer(translationX = animatedProgress.value)

    Card(
        onClick = {
            val screenshots = screenShots?.map { it?.image }
            onItemClick?.invoke(id, screenshots)
        },
        modifier = animatedModifier
            .padding(
                vertical = dimensionResource(id = R.dimen.dimen_8),
                horizontal = dimensionResource(id = R.dimen.dimen_8)
            ),
        elevation = dimensionResource(id = R.dimen.dimen_8),
        shape = RoundedCornerShape(
            dimensionResource(id = R.dimen.dimen_16)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .gradientBackground(color = backgroundColor, 45f)
        ) {

            // Metascore
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
                        childModifier = childModifier.padding(
                            horizontal = 12.dp,
                            vertical = 2.dp
                        )
                    )
                }
            }

            // image & name
            Row(
                modifier = modifier
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

            // date & rating
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = dimensionResource(id = R.dimen.dimen_8),
                        horizontal = dimensionResource(id = R.dimen.dimen_8)
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (rating != null) {
                    RatingBar(
                        rating = rating,
                        modifier = Modifier
                            .height(12.dp)
                            .padding(horizontal = dimensionResource(id = R.dimen.dimen_8))
                            .then(childModifier),
                        color = ratingColor ?: Color.Yellow
                    )
                }
                if (released != null) {
                    Text(
                        modifier = Modifier
                            .padding(
                                horizontal = dimensionResource(id = R.dimen.dimen_8),
                                vertical = dimensionResource(id = R.dimen.dimen_8)
                            )
                            .then(childModifier),
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
