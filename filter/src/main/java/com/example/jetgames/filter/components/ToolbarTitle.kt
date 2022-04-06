package com.example.jetgames.filter.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ToolbarTitle(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Start,
        text = "Filter",
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.onPrimary
    )
}
