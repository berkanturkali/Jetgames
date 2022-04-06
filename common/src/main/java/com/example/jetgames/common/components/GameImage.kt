package com.example.jetgames.common.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.ImageLoader
import coil.compose.rememberImagePainter

@Composable
fun GameImage(
    modifier: Modifier = Modifier,
    description: String?,
    image: String?,
    imageLoader: ImageLoader,
) {
    val painter = rememberImagePainter(
        data = image,
        imageLoader = imageLoader,
        builder = { crossfade(true) }
    )

    Image(
        modifier = modifier,
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop,
        painter = painter,
        contentDescription = description
    )
}
