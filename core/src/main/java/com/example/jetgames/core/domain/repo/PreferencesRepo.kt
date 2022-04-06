package com.example.jetgames.core.domain.repo

import com.example.jetgames.core.domain.model.preferences.HomePreferences
import kotlinx.coroutines.flow.Flow

interface PreferencesRepo {

    suspend fun insertHomePreferences(homePreferences: HomePreferences): Long

    fun preferences(): Flow<HomePreferences.HomeFilterPreferences>
}
