package com.example.jetgames.core.di

import com.example.jetgames.core.data.repo.GamesRepoImpl
import com.example.jetgames.core.domain.repo.GamesRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface RepoModule {

    @get:Binds
    val GamesRepoImpl.gamesRepo: GamesRepo
}