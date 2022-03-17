package com.example.jetgames.core.domain.model.preferences

data class OrderPreference(
    val isSelected:Boolean = true,
    val order:Order =Order.METACRITIC,
    val ascDesc: ASCDESC = ASCDESC.DESCENDING
){
    fun mapOrderToString():String{
        return ascDesc.name + order.name
    }
}