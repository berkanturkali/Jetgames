package com.example.jetgames.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.R
import com.example.jetgames.core.domain.model.games.Genre

@Composable
fun Genres(
    modifier: Modifier = Modifier,
    genres: List<Genre?>,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            text = stringResource(id = R.string.genres),
            style = MaterialTheme.typography.h6,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onPrimary
        )

        Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8)),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp)) {
            genres.forEach { genre ->
                Text(
                    modifier = Modifier
                        .shadow(elevation = dimensionResource(id = R.dimen.dimen_4),
                            shape = CircleShape,
                            clip = false)
                        .padding(horizontal = dimensionResource(
                            id = R.dimen.dimen_8),
                            vertical = dimensionResource(id = R.dimen.dimen_4)),
                    text = genre?.name.toString(),
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}