package com.example.jetgames.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.XXLightGray

@Composable
fun Screenshots(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    screenshots: List<String?>,
) {

    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.dimen_8))
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(text = stringResource(id = R.string.screenshots),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onPrimary)

        Divider(thickness = 0.5.dp, color = XXLightGray)
        LazyRow(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_8)),
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.dimen_8))) {
            items(screenshots.size) {
                Card(
                    modifier = Modifier.wrapContentSize(),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.dimen_8))) {
                    val painter =
                        rememberImagePainter(data = screenshots.get(it), imageLoader = imageLoader)
                    Image(
                        modifier = Modifier.size(200.dp),
                        painter = painter,
                        contentScale = ContentScale.Crop,
                        contentDescription = "")
                }
            }
        }
    }
}