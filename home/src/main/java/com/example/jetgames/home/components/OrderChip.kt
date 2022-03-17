package com.example.jetgames.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ChipDefaults
import androidx.compose.material.FilterChip
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.ui.theme.JetgamesTheme

@Composable
fun OrderChip(
    modifier: Modifier = Modifier,
    name: String,
    isSelected: Boolean,
    onSelectionChange: (String) -> Unit = {},
) {
    FilterChip(
        modifier = modifier,
        shape = CircleShape,
        border = if (!isSelected) BorderStroke(0.5.dp,
            color = MaterialTheme.colors.onSurface) else null,
        colors = ChipDefaults.filterChipColors(
            backgroundColor = Color.Transparent,
            selectedBackgroundColor = MaterialTheme.colors.secondary,
            selectedContentColor = MaterialTheme.colors.onSecondary,
        ),
        selected = isSelected,
        selectedIcon = { SelectedOrderChipIcon() },
        onClick = {
            onSelectionChange(name)
        }) {
        Text(text = name)
    }
}

@Preview
@Composable
fun OrderChipPrev() {
    JetgamesTheme {
        OrderChip(name = "Chip", isSelected = true)
    }
}