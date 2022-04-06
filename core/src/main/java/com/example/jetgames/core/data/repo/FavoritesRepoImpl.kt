package com.example.jetgames.core.data.repo

import com.example.jetgames.core.cache.abstraction.FavoritesCache
import com.example.jetgames.core.cache.mapper.favorites.FavoriteEntityMapper
import com.example.jetgames.core.domain.model.favorites.Favorite
import com.example.jetgames.core.domain.repo.FavoritesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class FavoritesRepoImpl @Inject constructor(
    private val favoritesCache: FavoritesCache,
    private val favoriteEntityMapper: FavoriteEntityMapper,
) : FavoritesRepo {
    override suspend fun upsert(favorite: Favorite): Long {
        val favEntity = favoriteEntityMapper.mapToEntity(favorite)
        return favoritesCache.upsert(favEntity)
    }

    override fun favorites(): Flow<List<Favorite>> {
        return favoritesCache.favorites().mapLatest {
            favoriteEntityMapper.mapTypeList(it) ?: emptyList()
        }
    }

    override suspend fun delete(game: Favorite) {
        val entity = favoriteEntityMapper.mapToEntity(game)
        favoritesCache.delete(entity)
    }

    override suspend fun clear() {
        favoritesCache.clear()
    }

    override suspend fun favorite(id: Int): Favorite {
        return favoriteEntityMapper.mapFromEntity(favoritesCache.favorite(id))
    }
}
