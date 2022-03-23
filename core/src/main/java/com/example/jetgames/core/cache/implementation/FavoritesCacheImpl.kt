package com.example.jetgames.core.cache.implementation

import com.example.jetgames.core.cache.abstraction.FavoritesCache
import com.example.jetgames.core.cache.dao.FavoritesDao
import com.example.jetgames.core.cache.model.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesCacheImpl @Inject constructor(
    private val dao: FavoritesDao,
) : FavoritesCache {
    override suspend fun upsert(favoriteEntity: FavoriteEntity): Long {
        return dao.upsert(favoriteEntity)
    }

    override fun favorites(): Flow<List<FavoriteEntity>> {
        return dao.favorites()
    }

    override suspend fun delete(favoriteEntity: FavoriteEntity) {
        return dao.delete(favoriteEntity)
    }

    override suspend fun clear() {
        return dao.clear()
    }
}