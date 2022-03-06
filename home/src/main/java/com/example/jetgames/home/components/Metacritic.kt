package com.example.jetgames.home.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MetaCritic(
    metaCritic: Int,
    ratingColor: Color,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    childModifier: Modifier = Modifier,
    isLoading: Boolean,
) {
    Card(
        modifier = modifier,
        elevation = 8.dp,
        shape = RoundedCornerShape(6.dp),
        backgroundColor = if(isLoading) Color.Transparent else ratingColor,
    ) {
        Text(
            modifier = childModifier,
            text = metaCritic.toString(),
            fontSize = fontSize,
            style = MaterialTheme.typography.h6,
            maxLines = 1,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary
        )
    }
}