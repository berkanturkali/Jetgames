package com.example.jetgames.core.data.contract

import com.example.jetgames.core.remote.model.genres.GenreDto
import com.example.jetgames.core.remote.model.platforms.PlatformDto

interface FilterRemote {

    suspend fun fetchPlatforms(): List<PlatformDto>

    suspend fun fetchGenres(): List<GenreDto>
}