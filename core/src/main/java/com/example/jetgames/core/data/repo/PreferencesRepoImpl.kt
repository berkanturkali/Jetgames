package com.example.jetgames.core.data.repo

import com.example.jetgames.core.cache.abstraction.HomeFilterPreferencesCache
import com.example.jetgames.core.cache.mapper.preferences.HomeFilterPreferencesEntityMapper
import com.example.jetgames.core.domain.model.preferences.HomePreferences
import com.example.jetgames.core.domain.repo.PreferencesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class PreferencesRepoImpl @Inject constructor(
    private val preferencesMapper: HomeFilterPreferencesEntityMapper,
    private val preferencesCache: HomeFilterPreferencesCache,
) : PreferencesRepo {

    override suspend fun insertHomePreferences(homePreferences: HomePreferences): Long {
        return preferencesCache.upsert(preferencesMapper.mapToEntity(homePreferences as HomePreferences.HomeFilterPreferences))
    }

    override fun preferences(): Flow<HomePreferences.HomeFilterPreferences> {
        val prefFlow = preferencesCache.preferences().mapLatest {
            preferencesMapper.mapFromEntity(it)
        }
        return prefFlow
    }
}
