package com.example.jetgames.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.example.jetgames.common.ui.theme.XLightGray
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer


@Composable
fun GameImage(
    modifier: Modifier = Modifier,
    description: String?,
    image: String?,
    imageLoader: ImageLoader,
    isLoading: Boolean = false,
) {
    val painter = rememberImagePainter(
        data = image,
        imageLoader = imageLoader,
        builder = { crossfade(true) }
    )

    Image(
        modifier = modifier
            .placeholder(visible = isLoading, highlight = PlaceholderHighlight.shimmer(XLightGray))
            .height(300.dp)
            .fillMaxWidth(),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop,
        painter = painter,
        contentDescription = description)
}