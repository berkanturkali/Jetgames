package com.example.jetgames.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.core.domain.model.games.Rating

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    ratings: List<Rating?>,
) {
    Column(modifier = modifier
        .padding(horizontal = dimensionResource(id = R.dimen.dimen_16),
            vertical = dimensionResource(
                id = R.dimen.dimen_8))
        .fillMaxWidth()
        .wrapContentHeight()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.dimen_64))
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
                ratings.forEachIndexed { index, rating ->

                    val colorList = when (index) {
                        0 -> listOf(Color(0XFFB4EC51), Color(0XFF429321))
                        1 -> listOf(Color(0XFF4354b9), Color(0xFF649bff))
                        2 -> listOf(Color(0XFFfad961), Color(0xFFf76b1c))
                        3 -> listOf(Color(0XFFff5764), Color(0XFFff5764))
                        else -> listOf(Color(0xFFFF5764), Color(0xFFF11A2A))
                    }
                    Box(modifier = Modifier
                        .weight(rating!!.percent!!.toFloat())
                        .fillMaxHeight()
                        .background(brush = Brush.verticalGradient(colorList)),
                        contentAlignment = Center) {
                        Text(text = rating.icon!!, fontSize = 12.sp)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))


        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceAround) {
            ratings.forEachIndexed { index, rating ->
                val color = when (index) {
                    0 -> 0xFFB4EC51
                    1 -> 0xFF4354B9
                    2 -> 0XFFFAD961
                    3 -> 0XFFFF5764
                    else -> 0XFFFFFFFF
                }
                Card(shape = CircleShape, modifier = Modifier.wrapContentSize()) {
                    Column(horizontalAlignment = CenterHorizontally,modifier = Modifier.padding(8.dp)) {
                        Row(verticalAlignment = CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                dimensionResource(id = R.dimen.dimen_4))) {
                            Icon(contentDescription = null,
                                modifier = Modifier.size(dimensionResource(id = R.dimen.dimen_8)),
                                painter = painterResource(id = R.drawable.ic_circle),
                                tint = Color(color))
                            Text(
                                text = rating!!.title!!,
                                color = MaterialTheme.colors.onPrimary,
                                style = MaterialTheme.typography.caption)
                        }
                            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                Text(text = rating!!.count.toString(),
                                    style = MaterialTheme.typography.overline)
                            }
                    }
                }
            }
        }
    }
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