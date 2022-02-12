package com.example.jetgames.core.di

import android.util.Log
import com.example.jetgames.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface LoggerModule {

    companion object{
        @[Provides Singleton]
        fun provideTimberTree():Timber.Tree = object : Timber.DebugTree(){
            override fun isLoggable(tag: String?, priority: Int): Boolean {
                return BuildConfig.DEBUG || priority >= Log.INFO
            }
        }
    }
}