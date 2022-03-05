package com.example.jetgames.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.R
import com.example.jetgames.common.components.RatingTop
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.common.ui.theme.XXLightGray
import com.example.jetgames.core.domain.model.detail.GameDetails
import kotlin.math.min

@Composable
fun RatingMetacriticSection(
    modifier: Modifier = Modifier,
    metacritic: Int,
    metacriticColor: Color,
    rating: Int,
    ratingColor: Color,

    ) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(120.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .weight(0.5f)
                .wrapContentHeight()
                .padding(vertical = dimensionResource(id = R.dimen.dimen_8))) {
            Text(text = stringResource(id = R.string.metacritic),
                modifier = Modifier.align(CenterHorizontally),
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onPrimary
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_16)))

            MetaCritic(metaCritic = metacritic, ratingColor = metacriticColor)

        }
        Divider(color = XXLightGray.copy(alpha = 0.5f),
            modifier = Modifier
                .fillMaxHeight()
                .width(0.5.dp))

        Column(horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .wrapContentHeight()
                .weight(0.5f)
                .padding(vertical = dimensionResource(id = R.dimen.dimen_8))) {
            Text(text = stringResource(id = R.string.rating_title),
                modifier = Modifier.align(CenterHorizontally),
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onPrimary
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_8)))
            Text(text = "$rating / 5",
                modifier = Modifier.align(CenterHorizontally),
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onPrimary)

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_8)))

            RatingTop(rating = rating.toDouble(), color = ratingColor, showTitle = false)
        }
    }
}

@Preview
@Composable
fun RatingMetacriticSectionPrev() {
    JetgamesTheme {
        val rating = 5
        val percent =if(rating == 1)((rating -1) * 0.2) else rating * 0.2
        val red = 255
        val green = percent * 255
        val blue = 0
        val color = Color(red,green.toInt(), blue)
        RatingMetacriticSection(
            ratingColor = color,
            rating = rating,
            metacriticColor = Color.Green,
            metacritic = 85)
    }
}