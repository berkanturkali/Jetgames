package com.example.jetgames.remote.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
public class MoshiModule {

    @[Provides Singleton]
    public fun providesMoshi(): Moshi =  Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}
