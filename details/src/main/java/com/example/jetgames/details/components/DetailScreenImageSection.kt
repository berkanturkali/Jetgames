package com.example.jetgames.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.example.jetgames.common.R

@Composable
fun DetailScreenImageSection(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    imageUrl: String? = null,
    imageLoader: ImageLoader,
    description: String? = null,
) {
    val painter = rememberImagePainter(
        data = imageUrl,
        imageLoader = imageLoader
    )

    Card(shape = RoundedCornerShape(dimensionResource(id = R.dimen.dimen_16)),
        modifier = modifier.fillMaxSize()) {
            if (imageUrl != null)
                Image(
                    modifier = childModifier
                        .fillMaxWidth()
                        .height(350.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    painter = painter,
                    contentDescription = description
                )
        }
}