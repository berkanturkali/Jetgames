package com.example.jetgames.core.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "homeFilterPreferences")
data class HomeFilterPreferencesEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val platforms: List<PlatformEntity>?,
    val genres: List<String>?,
    val minMetacri: Int,
    val maxMetacri: Int,
    val order: String,
    val direction: Char
)
