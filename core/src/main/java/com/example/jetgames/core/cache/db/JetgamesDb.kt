package com.example.jetgames.core.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetgames.core.BuildConfig
import com.example.jetgames.core.cache.model.PlatformEntity

@Database(
    entities = [PlatformEntity::class],
    version = BuildConfig.databaseVersion,
    exportSchema = false
)
abstract class JetgamesDb : RoomDatabase() {

    companion object{
        fun build(context: Context): JetgamesDb = Room.databaseBuilder(
            context.applicationContext,
            JetgamesDb::class.java,
            BuildConfig.databaseName
        ).fallbackToDestructiveMigration().build()
    }
}