package com.example.jetgames.core.cache.abstraction

import com.example.jetgames.core.cache.model.HomeFilterPreferencesEntity
import kotlinx.coroutines.flow.Flow

interface HomeFilterPreferencesCache {

    suspend fun upsert(filterPreferencesEntity:HomeFilterPreferencesEntity):Long

    fun preferences(): Flow<HomeFilterPreferencesEntity>
}