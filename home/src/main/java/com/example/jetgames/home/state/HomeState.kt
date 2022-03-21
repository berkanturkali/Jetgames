package com.example.jetgames.home.state

import com.example.jetgames.core.domain.model.preferences.HomePreferences
import com.example.jetgames.core.domain.model.preferences.OrderPreference

data class HomeState(
    var isRefreshing:Boolean =false,
    var filterCount:Int = 0,
    val homeViewPreferences: HomePreferences.HomeViewPreferences = HomePreferences.HomeViewPreferences(),
    val homeFilterPreferences: HomePreferences.HomeFilterPreferences = HomePreferences.HomeFilterPreferences()
)