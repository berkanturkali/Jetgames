package com.example.jetgames.core.domain.repo

import com.example.jetgames.core.domain.model.detail.GameDetails
import kotlinx.coroutines.flow.Flow

interface FavoritesRepo {

    suspend fun upsert(favoriteEntity: GameDetails): Long

    fun favorites(): Flow<List<GameDetails>>

    suspend fun delete(game: GameDetails)

    suspend fun clear()
}