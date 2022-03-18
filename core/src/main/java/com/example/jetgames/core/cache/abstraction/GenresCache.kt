package com.example.jetgames.core.cache.abstraction

import com.example.jetgames.core.cache.model.GenreEntity
import kotlinx.coroutines.flow.Flow

interface GenresCache {

    suspend fun upsert(genreEntity: GenreEntity): Long

    fun genres(): Flow<List<GenreEntity>>

    suspend fun insertAll(genres: List<GenreEntity>)

    suspend fun clear()
}