package com.example.jetgames.core.domain.repo

import com.example.jetgames.core.domain.model.genres.Genre
import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface FiltersRepo {

    fun fetchPlatforms(refresh: Boolean): Flow<Resource<List<Platform>>>

    fun fetchGenres(refresh: Boolean): Flow<Resource<List<Genre>>>
}
