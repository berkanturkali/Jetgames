package com.example.jetgames.core.cache.implementation

import com.example.jetgames.core.cache.abstraction.HomeFilterPreferencesCache
import com.example.jetgames.core.cache.dao.HomeFilterPreferencesDao
import com.example.jetgames.core.cache.model.HomeFilterPreferencesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeFilterPreferencesCacheImpl @Inject constructor(
    private val dao: HomeFilterPreferencesDao,
) : HomeFilterPreferencesCache {
    override suspend fun upsert(filterPreferencesEntity: HomeFilterPreferencesEntity):Long {
       return dao.upsert(filterPreferencesEntity = filterPreferencesEntity)
    }

    override fun preferences(): Flow<HomeFilterPreferencesEntity> {
        return dao.preferences()
    }
}