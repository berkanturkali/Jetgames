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
import androidx.compose.ui.unit.dp
import com.example.jetgames.core.domain.model.preferences.OrderPreference

@Composable
fun OrderChip(
    modifier: Modifier = Modifier,
    order: OrderPreference,
    isSelected: Boolean,
    onSelectionChange: (OrderPreference) -> Unit = {},
) {
    FilterChip(
        modifier = modifier,
        shape = CircleShape,
        border = if (!isSelected) BorderStroke(0.5.dp,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f)) else null,
        colors = ChipDefaults.filterChipColors(
            backgroundColor = Color.Transparent,
            selectedBackgroundColor = MaterialTheme.colors.secondary,
            selectedContentColor = MaterialTheme.colors.onSecondary,
        ),
        selected = isSelected,
        selectedIcon = { SelectedOrderChipIcon() },
        onClick = {
            onSelectionChange(order)
        }) {
        Text(text = order.order!!.value)
    }
}