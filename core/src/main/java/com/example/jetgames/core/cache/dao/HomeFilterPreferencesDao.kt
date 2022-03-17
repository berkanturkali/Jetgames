package com.example.jetgames.core.cache.dao

import androidx.room.*
import com.example.jetgames.core.cache.model.HomeFilterPreferencesEntity
import com.example.jetgames.core.domain.model.preferences.HomePreferences
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeFilterPreferencesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(filterPreferencesEntity: HomeFilterPreferencesEntity):Long

    @Query("SELECT * FROM homeFilterPreferences")
    fun preferences(): Flow<HomeFilterPreferencesEntity>
}