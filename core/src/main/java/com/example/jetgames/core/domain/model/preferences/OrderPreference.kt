package com.example.jetgames.core.domain.model.preferences

data class OrderPreference(
    val order: Order = Order.METACRITIC,
    val ascDesc: ASCDESC = ASCDESC.DESCENDING
) {
    fun mapOrderToString(): String {
        return ascDesc.value + order.value
    }
}
