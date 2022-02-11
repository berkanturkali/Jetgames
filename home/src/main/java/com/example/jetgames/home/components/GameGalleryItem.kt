package com.example.jetgames.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.jetgames.common.R
import com.example.jetgames.core.domain.model.games.Game

@Composable
fun GameGalleryItem(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    game: Game,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = dimensionResource(id = R.dimen.dimen_8),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.dimen_16)),
        onClick = {}
    ) {
        Column(
            modifier = modifier
        ) {

            //image

        }
    }
}

@Composable
fun GameImage(
    modifier: Modifier = Modifier,
    description: String?,
    image: String?,
) {


}