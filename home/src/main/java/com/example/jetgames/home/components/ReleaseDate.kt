package com.example.jetgames.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetgames.common.R

@Composable
fun ReleaseDate(
    released: String?,
    size: TextUnit = 14.sp,
) {
    Text(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
        text = stringResource(id = R.string.release_date),
        fontSize = size,
        style = MaterialTheme.typography.body1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.onPrimary
    )

    Text(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        text = released ?: "",
        fontSize = size,
        style = MaterialTheme.typography.h5,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.onPrimary
    )
}
