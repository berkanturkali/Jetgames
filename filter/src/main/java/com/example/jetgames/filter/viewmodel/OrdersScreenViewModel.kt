package com.example.jetgames.filter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jetgames.core.domain.model.preferences.Order
import com.example.jetgames.core.domain.model.preferences.OrderPreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OrdersScreenViewModel : ViewModel() {


    private val _orders = MutableStateFlow<List<String>>(orderOptions())

    val orders: StateFlow<List<String>> get() = _orders


    private fun orderOptions(): List<String> {
        return listOf(
            OrderPreference(
                order = Order.RATING
            ),
            OrderPreference(
                order = Order.METACRITIC
            ),
        ).map {
            it.order.value
        }
    }
}