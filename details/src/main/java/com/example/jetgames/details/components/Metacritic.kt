package com.example.jetgames.details.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MetaCritic(
    metaCritic: Int,
    ratingColor: Color,
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        elevation = 8.dp,
        border = BorderStroke(1.dp, ratingColor),
        shape = RoundedCornerShape(6.dp),
        backgroundColor = Color.Transparent,
    ) {
        Text(
            modifier = childModifier.padding(horizontal = 12.dp, vertical = 2.dp),
            text = metaCritic.toString(),
            fontSize = 20.sp,
            style = MaterialTheme.typography.h3,
            maxLines = 1,
            textAlign = TextAlign.Center,
            color = ratingColor
        )
    }
}