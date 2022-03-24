package com.example.jetgames.core.cache.dao

import androidx.room.*
import com.example.jetgames.core.cache.model.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(favoriteEntity: FavoriteEntity): Long

    @Query("SELECT * FROM favorites ORDER BY date DESC")
    fun favorites(): Flow<List<FavoriteEntity>>

    @Delete
    suspend fun delete(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun favorite(id: Int):FavoriteEntity?

    @Query("DELETE FROM favorites")
    suspend fun clear()
}