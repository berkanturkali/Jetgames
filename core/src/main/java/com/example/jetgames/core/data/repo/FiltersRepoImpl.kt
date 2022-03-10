package com.example.jetgames.core.data.repo

import com.example.jetgames.core.cache.db.JetgamesDb
import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.core.domain.repo.FiltersRepo
import com.example.jetgames.core.remote.mapper.platforms.PlatformMapper
import com.example.jetgames.core.remote.service.ApiService
import com.example.jetgames.core.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FiltersRepoImpl @Inject constructor(
    private val platformMapper: PlatformMapper,
    private val db:JetgamesDb,
    private val service:ApiService,


) : FiltersRepo {
    override suspend fun fetchPlatforms(): Flow<List<Platform>> = flow {}
}