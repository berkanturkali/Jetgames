package com.example.jetgames.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetgames.common.R
import com.example.jetgames.core.domain.model.games.Genre

@Composable
fun Genres(
    genres: List<Genre?>,
    fontSize: TextUnit = 14.sp,
    color: Color,
) {
    Text(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
        text = stringResource(id = R.string.genres),
        fontSize = fontSize,
        style = MaterialTheme.typography.body1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.onPrimary
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8)),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        items(genres) {
            Text(
                modifier = Modifier
                    .shadow(
                        elevation = dimensionResource(id = R.dimen.dimen_4),
                        shape = CircleShape,
                        clip = false
                    )
                    .background(
                        Brush.verticalGradient(listOf(color, color.copy(alpha = 0.6f))),
                        shape = CircleShape
                    )
                    .padding(
                        horizontal = dimensionResource(
                            id = R.dimen.dimen_8
                        ),
                        vertical = dimensionResource(id = R.dimen.dimen_4)
                    ),
                text = it?.name.toString(),
                fontSize = fontSize,
                style = MaterialTheme.typography.h5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}
