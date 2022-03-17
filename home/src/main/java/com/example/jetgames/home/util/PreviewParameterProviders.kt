package com.example.jetgames.home.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jetgames.core.domain.model.preferences.Order
import com.example.jetgames.core.domain.model.preferences.OrderPreference

class OrderPreferencesProvider:PreviewParameterProvider<List<OrderPreference>>{
    override val values: Sequence<List<OrderPreference>>
        get() =  sequenceOf(listOf(
            OrderPreference(
                order = Order.NAME
            ),
            OrderPreference(
                order = Order.RELEASED
            ),
            OrderPreference(
                order = Order.RATING
            ),
            OrderPreference(
                order = Order.METACRITIC
            ),
        ))
}