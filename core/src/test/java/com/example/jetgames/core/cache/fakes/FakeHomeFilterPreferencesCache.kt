package com.example.jetgames.core.cache.fakes

import com.example.jetgames.core.cache.abstraction.HomeFilterPreferencesCache
import com.example.jetgames.core.cache.model.HomeFilterPreferencesEntity
import com.example.jetgames.core.domain.model.preferences.HomePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FakeHomeFilterPreferencesCache : HomeFilterPreferencesCache {

    private val cache = LinkedHashMap<Int, HomeFilterPreferencesEntity>()

    override suspend fun upsert(filterPreferencesEntity: HomeFilterPreferencesEntity): Long {
        cache[filterPreferencesEntity.id] = filterPreferencesEntity
        return filterPreferencesEntity.id.toLong()
    }

    override fun preferences(): Flow<HomeFilterPreferencesEntity> {
        return flow {
            emit(cache.values.first())
        }.catch {
            emit(HomeFilterPreferencesEntity(platforms = null))
        }
    }
}