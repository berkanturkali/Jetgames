package com.example.jetgames.home.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jetgames.core.domain.model.preferences.Order
import com.example.jetgames.core.domain.model.preferences.OrderPreference

class OrderPreferencesProvider:PreviewParameterProvider<List<OrderPreference>>{
    override val values: Sequence<List<OrderPreference>>
        get() =  sequenceOf(listOf(
            OrderPreference(
                isSelected = false,
                order = Order.NAME
            ),
            OrderPreference(
                isSelected = false,
                order = Order.RELEASED
            ),
            OrderPreference(
                isSelected = false,
                order = Order.RATING
            ),
            OrderPreference(
                isSelected = true,
                order = Order.METACRITIC
            ),
        ))

}