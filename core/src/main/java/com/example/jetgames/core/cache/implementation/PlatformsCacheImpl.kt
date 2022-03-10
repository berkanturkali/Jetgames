package com.example.jetgames.core.cache.implementation

import com.example.jetgames.core.cache.abstraction.PlatformsCache
import com.example.jetgames.core.cache.dao.PlatformsDao
import com.example.jetgames.core.cache.model.PlatformEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlatformsCacheImpl @Inject constructor(
    private val dao:PlatformsDao
):PlatformsCache {
    override suspend fun upsert(platformEntity: PlatformEntity): Long {
        return dao.upsert(platformEntity)
    }

    override fun platforms(): Flow<List<PlatformEntity>> {
        return dao.platforms()
    }

    override suspend fun insertAll(platforms: List<PlatformEntity>) {
        dao.insertAll(platforms = platforms)
    }

    override suspend fun clear() {
        dao.clear()
    }
}