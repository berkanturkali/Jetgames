package com.example.jetgames.core.di

import android.app.Application
import coil.ImageLoader
import com.example.jetgames.core.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface CoilModule {

    companion object {
        @Provides
        fun provideImageLoader(app: Application) = ImageLoader.Builder(app)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.ic_game)
            .availableMemoryPercentage(0.25)
            .crossfade(true)
            .build()
    }
}