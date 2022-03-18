package com.example.jetgames.core.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetgames.core.BuildConfig
import com.example.jetgames.core.cache.converters.RoomConverters
import com.example.jetgames.core.cache.dao.GenresDao
import com.example.jetgames.core.cache.dao.HomeFilterPreferencesDao
import com.example.jetgames.core.cache.dao.PlatformsDao
import com.example.jetgames.core.cache.model.GenreEntity
import com.example.jetgames.core.cache.model.HomeFilterPreferencesEntity
import com.example.jetgames.core.cache.model.PlatformEntity
import com.squareup.moshi.Moshi

@Database(
    entities = [PlatformEntity::class, HomeFilterPreferencesEntity::class,GenreEntity::class],
    version = BuildConfig.databaseVersion,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class JetgamesDb : RoomDatabase() {

    abstract val platformsDao: PlatformsDao

    abstract val homeFilterPreferencesDao: HomeFilterPreferencesDao

    abstract val genresDao:GenresDao

    companion object {
        fun build(context: Context): JetgamesDb = Room.databaseBuilder(
            context.applicationContext,
            JetgamesDb::class.java,
            BuildConfig.databaseName
        )
            .fallbackToDestructiveMigration().build()
    }
}