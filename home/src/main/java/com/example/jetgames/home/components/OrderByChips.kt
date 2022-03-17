package com.example.jetgames.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.core.domain.model.preferences.OrderPreference
import com.example.jetgames.home.util.OrderPreferencesProvider

@Composable
fun OrderByChips(
    modifier: Modifier = Modifier,
    orderOptions: List<OrderPreference>,
) {
    LazyRow(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(dimensionResource(id = R.dimen.dimen_8)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {
        items(orderOptions) {
            OrderChip(name = it.order.value, isSelected = it.isSelected)
        }
    }
}

@Preview
@Composable
fun OrderByChipsPrev(
    @PreviewParameter(OrderPreferencesProvider::class) preferences: List<OrderPreference>,
) {
    JetgamesTheme {
        OrderByChips(orderOptions = preferences)
    }
}
