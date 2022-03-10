package com.example.jetgames.core.di

import android.content.Context
import com.example.jetgames.core.cache.dao.PlatformsDao
import com.example.jetgames.core.cache.db.JetgamesDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
internal object CacheModule {

    @[Provides Singleton]
    fun provideDb(@ApplicationContext context: Context): JetgamesDb {
        return JetgamesDb.build(context = context)
    }

    @[Provides Singleton]
    fun providePlatformsDao(db: JetgamesDb): PlatformsDao = db.platformsDao
}