package com.example.jetgames.core.di

import com.example.jetgames.core.BuildConfig
import com.example.jetgames.core.data.contract.GameDetailRemote
import com.example.jetgames.core.data.contract.GamesRemote
import com.example.jetgames.core.remote.impl.GameDetailRemoteImpl
import com.example.jetgames.core.remote.impl.GamesRemoteImpl
import com.example.jetgames.core.remote.service.ApiService
import com.example.jetgames.remote.factory.RemoteFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface RemoteModule {

    @get:Binds
    val GamesRemoteImpl.bindGamesRemote: GamesRemote

    @get:Binds
    val GameDetailRemoteImpl.bindGameDetailRemote:GameDetailRemote

    companion object {
        @[Provides Singleton]
        fun apiService(remoteFactory: RemoteFactory): ApiService =
            remoteFactory.createRetrofit(BuildConfig.BASE_URL, BuildConfig.DEBUG)
                .create(ApiService::class.java)
    }
}