package com.example.jetgames.core.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetgames.core.cache.model.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(platformEntity: GenreEntity): Long

    @Query("SELECT * FROM genres")
    fun platforms(): Flow<List<GenreEntity>>

    @Insert
    suspend fun insertAll(platforms: List<GenreEntity>)

    @Query("DELETE FROM genres")
    suspend fun clear()
}