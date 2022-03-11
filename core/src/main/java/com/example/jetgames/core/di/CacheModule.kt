package com.example.jetgames.core.di

import com.example.jetgames.core.cache.abstraction.PlatformsCache
import com.example.jetgames.core.cache.implementation.PlatformsCacheImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface CacheModule {

    @get:Binds
    val PlatformsCacheImpl.bindPlatformsCache:PlatformsCache
}