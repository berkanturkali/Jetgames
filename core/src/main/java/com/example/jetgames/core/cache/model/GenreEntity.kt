package com.example.jetgames.core.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val name:String
)