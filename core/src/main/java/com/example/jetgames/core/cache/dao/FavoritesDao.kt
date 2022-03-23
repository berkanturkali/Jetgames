package com.example.jetgames.core.cache.dao

import androidx.room.*
import com.example.jetgames.core.cache.model.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(favoriteEntity: FavoriteEntity): Long

    @Query("SELECT * FROM favorites")
    fun favorites(): Flow<List<FavoriteEntity>>

    @Delete
    suspend fun delete(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorites")
    suspend fun clear()
}