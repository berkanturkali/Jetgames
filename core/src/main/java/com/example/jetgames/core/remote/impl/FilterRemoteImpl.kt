package com.example.jetgames.core.remote.impl

import com.example.jetgames.core.data.contract.FilterRemote
import com.example.jetgames.core.remote.model.genres.GenreDto
import com.example.jetgames.core.remote.model.platforms.PlatformDto
import com.example.jetgames.core.remote.service.ApiService
import javax.inject.Inject

class FilterRemoteImpl @Inject constructor(
    private val apiService: ApiService,
) : FilterRemote {
    override suspend fun fetchPlatforms(): List<PlatformDto> {
        return apiService.fetchPlatforms().results
    }

    override suspend fun fetchGenres(): List<GenreDto> {
        return apiService.fetchGenres().genres
    }
}