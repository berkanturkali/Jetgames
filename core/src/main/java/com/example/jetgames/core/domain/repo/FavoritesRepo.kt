package com.example.jetgames.core.domain.repo

import com.example.jetgames.core.domain.model.favorites.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoritesRepo {

    suspend fun upsert(favorite: Favorite): Long

    fun favorites(): Flow<List<Favorite>>

    suspend fun delete(game: Favorite)

    suspend fun clear()

    suspend fun favorite(id:Int):Favorite
}