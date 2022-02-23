package com.example.jetgames.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme

@Composable
fun Released(
    modifier: Modifier = Modifier,
    released:String
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_8)),
            text = stringResource(id = R.string.release_date),
            style = MaterialTheme.typography.h6,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onPrimary
        )

        Text(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_8)),
            text = released,
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onPrimary
        )
    }
}


@Preview
@Composable
fun ReleasedPrev(){
    JetgamesTheme {
        Released(released = "12 Dec 2021")
    }
}