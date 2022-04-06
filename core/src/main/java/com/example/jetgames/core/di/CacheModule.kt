package com.example.jetgames.core.di

import com.example.jetgames.core.cache.abstraction.FavoritesCache
import com.example.jetgames.core.cache.abstraction.GenresCache
import com.example.jetgames.core.cache.abstraction.HomeFilterPreferencesCache
import com.example.jetgames.core.cache.abstraction.PlatformsCache
import com.example.jetgames.core.cache.implementation.FavoritesCacheImpl
import com.example.jetgames.core.cache.implementation.GenresCacheImpl
import com.example.jetgames.core.cache.implementation.HomeFilterPreferencesCacheImpl
import com.example.jetgames.core.cache.implementation.PlatformsCacheImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface CacheModule {

    @get:Binds
    val PlatformsCacheImpl.bindPlatformsCache: PlatformsCache

    @get:Binds
    val HomeFilterPreferencesCacheImpl.bindHomeFilterPreferencesCache: HomeFilterPreferencesCache

    @get:Binds
    val GenresCacheImpl.bindGenresCache: GenresCache

    @get:Binds
    val FavoritesCacheImpl.bindFavoritesCache: FavoritesCache
}
