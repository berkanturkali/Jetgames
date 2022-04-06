package com.example.jetgames.core.cache.abstraction

import com.example.jetgames.core.cache.model.PlatformEntity
import kotlinx.coroutines.flow.Flow

interface PlatformsCache {

    suspend fun upsert(platformEntity: PlatformEntity): Long

    fun platforms(): Flow<List<PlatformEntity>>

    suspend fun insertAll(platforms: List<PlatformEntity>)

    suspend fun clear()
}
