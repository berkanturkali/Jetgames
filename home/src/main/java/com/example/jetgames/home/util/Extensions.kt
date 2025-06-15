package com.example.jetgames.home.util

import com.example.jetgames.core.domain.model.preferences.Order
import com.example.jetgames.home.model.MetaCriticSeparatorGenerator
import com.example.jetgames.home.model.RatingSeparatorGenerator
import com.example.jetgames.home.model.SeparatorGenerator

fun Order.toSeparatorGenerator(): SeparatorGenerator = when (this) {
    Order.RATING -> RatingSeparatorGenerator
    Order.METACRITIC -> MetaCriticSeparatorGenerator
}