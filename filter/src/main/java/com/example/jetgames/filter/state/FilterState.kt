package com.example.jetgames.filter.state

import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.core.domain.model.preferences.MetacriticPreference
import com.example.jetgames.core.domain.model.preferences.OrderPreference

data class FilterState(
    val selectedPlatforms: List<Platform>? = null,
    val selectedGenres: List<String>? = null,
    val selectedMetacritics: MetacriticPreference? = null,
    val isApplyButtonVisible: Boolean = false,
    val selectedOrder: OrderPreference = OrderPreference(),
)
