package com.example.jetgames.core.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetgames.core.cache.model.PlatformEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlatformsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(platformEntity: PlatformEntity): Long

    @Query("SELECT * FROM platforms")
    fun platforms(): Flow<List<PlatformEntity>>

    @Insert
    suspend fun insertAll(platforms: List<PlatformEntity>)

    @Query("DELETE FROM platforms")
    suspend fun clear()
}