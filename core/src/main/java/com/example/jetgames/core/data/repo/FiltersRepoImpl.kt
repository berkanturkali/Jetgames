package com.example.jetgames.core.data.repo

import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.core.domain.repo.FiltersRepo
import com.example.jetgames.core.remote.mapper.platforms.PlatformMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FiltersRepoImpl @Inject constructor(
    private val platformMapper: PlatformMapper,
) : FiltersRepo {
    override suspend fun fetchPlatforms(): Flow<List<Platform>> = flow { }
}