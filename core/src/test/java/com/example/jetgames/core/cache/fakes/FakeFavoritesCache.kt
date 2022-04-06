package com.example.jetgames.core.cache.fakes

import com.example.jetgames.core.cache.abstraction.FavoritesCache
import com.example.jetgames.core.cache.model.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFavoritesCache : FavoritesCache {

    private val cache = LinkedHashMap<Int, FavoriteEntity>()

    override suspend fun upsert(favoriteEntity: FavoriteEntity): Long {
        cache[favoriteEntity.id] = favoriteEntity
        return favoriteEntity.id.toLong()
    }

    override fun favorites(): Flow<List<FavoriteEntity>> {
        return if (cache.isEmpty()) {
            flow { emit(emptyList()) }
        } else {
            flow { emit(cache.values.toList()) }
        }
    }

    override suspend fun delete(favoriteEntity: FavoriteEntity) {
        cache.remove(favoriteEntity.id)
    }

    override suspend fun favorite(id: Int): FavoriteEntity? {
        return cache[id]
    }

    override suspend fun clear() {
        cache.clear()
    }
}
