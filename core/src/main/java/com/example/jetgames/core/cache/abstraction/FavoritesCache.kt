package com.example.jetgames.core.cache.abstraction

import com.example.jetgames.core.cache.model.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoritesCache {

    suspend fun upsert(favoriteEntity: FavoriteEntity): Long

    fun favorites(): Flow<List<FavoriteEntity>>

    suspend fun delete(favoriteEntity: FavoriteEntity)

    suspend fun favorite(id: Int): FavoriteEntity?

    suspend fun clear()
}
