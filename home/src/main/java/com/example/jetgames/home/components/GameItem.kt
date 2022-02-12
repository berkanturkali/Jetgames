package com.example.jetgames.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.ImageLoader
import com.example.jetgames.common.R
import com.example.jetgames.core.domain.model.games.Game

@Composable
fun GameItem(
    modifier: Modifier = Modifier,
    game: Game,
    imageLoader: ImageLoader
) {
    Card(backgroundColor = Color.Transparent,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.dimen_8),
                horizontal = dimensionResource(id = R.dimen.dimen_8)),
        elevation = dimensionResource(id = R.dimen.dimen_8),
        shape = RoundedCornerShape(
            dimensionResource(id = R.dimen.dimen_16))) {

        ConstraintLayout(
        ) {
            val (imageTitleRow, metacritic, bottomRow) = createRefs()

            //Metascore
            if (game.metaCritic != null) {
                MetaCritic(metaCritic = game.metaCritic!!,
                    ratingColor = game.calculateRgbFromMetacritic()!!,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                        .constrainAs(metacritic) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            width = Dimension.wrapContent
                            height = Dimension.wrapContent
                        },
                    fontSize = 14.sp,
                    childModifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp))
            }

            //image & name
            Row(modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.dimen_16))
                .constrainAs(imageTitleRow) {
                    if (game.metaCritic != null) {
                        top.linkTo(metacritic.bottom)
                    } else {
                        top.linkTo(parent.top)
                    }
                    start.linkTo(parent.start)
                }) {
                GameImage(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
                    description = game.name,
                    image = game.backgroundImage,
                    imageLoader = imageLoader
                )
                if (game.name != null) {
                    val rating = game.ratings?.maxByOrNull {
                        it?.percent!!
                    }
                    Name(
                        name = game.name,
                        icon = rating?.icon,
                        size = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = dimensionResource(
                                id = R.dimen.dimen_8))
                    )
                }
            }

            //date & rating
            Row(modifier = Modifier
                .padding(vertical = dimensionResource(id = R.dimen.dimen_4),
                    horizontal = dimensionResource(id = R.dimen.dimen_8))
                .constrainAs(bottomRow) {
                    top.linkTo(imageTitleRow.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                },
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