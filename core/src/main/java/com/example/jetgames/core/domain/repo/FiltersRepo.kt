package com.example.jetgames.core.domain.repo

import com.example.jetgames.core.domain.model.platforms.Platform
import kotlinx.coroutines.flow.Flow

interface FiltersRepo {

    suspend fun fetchPlatforms():Flow<List<Platform>>
}