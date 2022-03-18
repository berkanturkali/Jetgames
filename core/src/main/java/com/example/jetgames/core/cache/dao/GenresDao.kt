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
    suspend fun upsert(genreEntity: GenreEntity): Long

    @Query("SELECT * FROM genres")
    fun genres(): Flow<List<GenreEntity>>

    @Insert
    suspend fun insertAll(genres: List<GenreEntity>)

    @Query("DELETE FROM genres")
    suspend fun clear()
}