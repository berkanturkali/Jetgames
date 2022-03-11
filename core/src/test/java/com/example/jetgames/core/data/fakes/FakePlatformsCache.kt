package com.example.jetgames.core.data.fakes

import com.example.jetgames.core.cache.abstraction.PlatformsCache
import com.example.jetgames.core.cache.model.PlatformEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePlatformsCache : PlatformsCache {

    private val cache = LinkedHashMap<Int, PlatformEntity>()

    override suspend fun upsert(platformEntity: PlatformEntity): Long {
        cache[platformEntity.id] = platformEntity
        return platformEntity.id.toLong()
    }

    override fun platforms(): Flow<List<PlatformEntity>> {
        return flow { cache.values.toList() }
    }

    override suspend fun insertAll(platforms: List<PlatformEntity>) {
        platforms.forEach{
            cache[it.id] = it
        }
    }

    override suspend fun clear() {
        cache.clear()
    }
}