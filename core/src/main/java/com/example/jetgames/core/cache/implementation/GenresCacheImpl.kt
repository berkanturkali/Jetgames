package com.example.jetgames.core.cache.implementation

import com.example.jetgames.core.cache.abstraction.GenresCache
import com.example.jetgames.core.cache.dao.GenresDao
import com.example.jetgames.core.cache.model.GenreEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenresCacheImpl @Inject constructor(
    private val dao:GenresDao
):GenresCache {
    override suspend fun upsert(genreEntity: GenreEntity): Long {
        return dao.upsert(genreEntity)
    }

    override fun genres(): Flow<List<GenreEntity>> {
        return dao.genres()
    }

    override suspend fun insertAll(genres: List<GenreEntity>) {
        return dao.insertAll(genres)
    }

    override suspend fun clear() {
        return dao.clear()
    }
}