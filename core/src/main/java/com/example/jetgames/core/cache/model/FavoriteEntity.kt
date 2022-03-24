package com.example.jetgames.core.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String?,
    val name: String,
    val rating: Double?,
    val releaseDate: String?,
    val metacritic: Int?,
    val icon: String?,
    val date: Long = System.currentTimeMillis(),
)