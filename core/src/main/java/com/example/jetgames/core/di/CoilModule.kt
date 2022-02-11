package com.example.jetgames.core.di

import android.app.Application
import coil.ImageLoader
import com.example.jetgames.common.R
import dagger.Provides
import javax.inject.Singleton

interface CoilModule {

    companion object{
        @[Provides Singleton]
        fun provideImageLoader(app: Application) = ImageLoader.Builder(app)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.ic_game)
            .availableMemoryPercentage(0.25)
            .crossfade(true)
            .build()
    }
}