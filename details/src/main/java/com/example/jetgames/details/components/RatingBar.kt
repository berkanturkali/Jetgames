package com.example.jetgames.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.core.domain.model.games.Rating

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    ratings: List<Rating>,
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(dimensionResource(id = com.example.jetgames.common.R.dimen.dimen_64))
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                ratings.forEach { rating ->
                    Box(modifier = Modifier
                        .weight(rating.percent!!.toFloat())
                        .background(brush = Brush.verticalGradient()))
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            ratings.forEach {
                Card(modifier = Modifier
                    .height(35.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {

                    }
                }
            }
        }
    }
}

private fun calculateBackgroundFromRatingPercentage(rating: Float): List<Color> {
    return emptyList()
}


@Preview
@Composable
fun RatingBarPrev() {
    JetgamesTheme {
        val ratings = listOf<Rating>(
            Rating(id = 5,
                count = 3244,
                title = "exceptional",
                icon = "\uD83C\uDFAF",
                percent = 58.98),
            Rating(id = 4,
                count = 1815,
                title = "recommended",
                icon = "\uD83D\uDC4D",
                percent = 33.0),
            Rating(id = 3, count = 349, title = "meh", icon = "\u26D4", percent = 6.35),
            Rating(id = 1, count = 92, title = "skip", icon = "\uD83D\uDE11", percent = 1.67),
        )
        RatingBar(ratings = ratings)
    }
}