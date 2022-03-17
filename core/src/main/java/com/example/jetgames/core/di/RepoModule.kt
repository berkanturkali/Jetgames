package com.example.jetgames.core.di

import com.example.jetgames.core.data.repo.FiltersRepoImpl
import com.example.jetgames.core.data.repo.GameDetailRepoImpl
import com.example.jetgames.core.data.repo.GamesRepoImpl
import com.example.jetgames.core.data.repo.PreferencesRepoImpl
import com.example.jetgames.core.domain.repo.FiltersRepo
import com.example.jetgames.core.domain.repo.GameDetailRepo
import com.example.jetgames.core.domain.repo.GamesRepo
import com.example.jetgames.core.domain.repo.PreferencesRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface RepoModule {

    @get:Binds
    val GamesRepoImpl.gamesRepo: GamesRepo

    @get:Binds
    val GameDetailRepoImpl.gameDetailRepo:GameDetailRepo

    @get:Binds
    val FiltersRepoImpl.bindFiltersRepo: FiltersRepo

    @get:Binds
    val PreferencesRepoImpl.bindPreferencesRepo: PreferencesRepo
}