package com.example.jetgames.core.di

import com.example.jetgames.core.data.repo.*
import com.example.jetgames.core.domain.repo.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface RepoModule {

    @get:Binds
    val GamesRepoImpl.gamesRepo: GamesRepo

    @get:Binds
    val GameDetailRepoImpl.gameDetailRepo: GameDetailRepo

    @get:Binds
    val FiltersRepoImpl.bindFiltersRepo: FiltersRepo

    @get:Binds
    val PreferencesRepoImpl.bindPreferencesRepo: PreferencesRepo

    @get:Binds
    val FavoritesRepoImpl.bindFavoritesRepo: FavoritesRepo
}