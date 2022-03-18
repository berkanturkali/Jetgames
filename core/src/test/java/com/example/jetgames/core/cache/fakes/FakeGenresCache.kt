package com.example.jetgames.core.cache.fakes

import com.example.jetgames.core.cache.abstraction.GenresCache
import com.example.jetgames.core.cache.model.GenreEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGenresCache : GenresCache {

    private val cache = LinkedHashMap<Int, GenreEntity>()

    override suspend fun upsert(genreEntity: GenreEntity): Long {
        cache[genreEntity.id] = genreEntity
        return genreEntity.id.toLong()
    }

    override fun genres(): Flow<List<GenreEntity>> {
        return if (cache.isEmpty()) {
            flow { emit(emptyList<GenreEntity>()) }
        } else {
            flow { emit(cache.values.toList()) }
        }
    }

    override suspend fun insertAll(genres: List<GenreEntity>) {
        genres.forEach {
            upsert(it)
        }
    }

    override suspend fun clear() {
        if (cache.isNotEmpty()) cache.clear()
    }
}