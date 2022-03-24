package com.example.jetgames.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetgames.common.ui.theme.JetgamesTheme


@Composable
fun MetaCritic(
    metaCritic: Int,
    ratingColor: Color?,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    childModifier: Modifier = Modifier,
    isLoading: Boolean,
) {
    Card(
        modifier = modifier,
        elevation = 8.dp,
        border = if (!isLoading) BorderStroke(1.dp, ratingColor ?: Color.Yellow) else BorderStroke(0.dp,
            Color.Transparent),
        shape = RoundedCornerShape(6.dp),
        backgroundColor = Color.Transparent,
    ) {
        Text(
            modifier = childModifier,
            text = metaCritic.toString(),
            fontSize = fontSize,
            style = MaterialTheme.typography.h3,
            maxLines = 1,
            textAlign = TextAlign.Center,
            color = ratingColor ?: Color.Transparent
        )
    }
}

@Preview
@Composable
fun MetacriPrev() {
    JetgamesTheme {
        MetaCritic(
            metaCritic = 95,
            ratingColor = Color.Green,
            isLoading = false
        )
    }
}