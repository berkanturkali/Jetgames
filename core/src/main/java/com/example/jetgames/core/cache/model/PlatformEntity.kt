package com.example.jetgames.core.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Platforms")
data class PlatformEntity(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val name:String,
)