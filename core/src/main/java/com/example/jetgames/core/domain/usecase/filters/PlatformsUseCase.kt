package com.example.jetgames.core.domain.usecase.filters

import com.example.jetgames.core.domain.executor.abstraction.PostExecutionThread
import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.core.domain.repo.FiltersRepo
import com.example.jetgames.core.domain.usecase.base.FlowUseCase
import com.example.jetgames.core.domain.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlatformsUseCase @Inject constructor(
    private val executor: PostExecutionThread,
    private val repo: FiltersRepo,
) : FlowUseCase<Boolean, Resource<List<Platform>>>() {
    override val dispatcher: CoroutineDispatcher
        get() = executor.io

    override fun execute(refresh: Boolean?): Flow<Resource<List<Platform>>> {
        return repo.fetchPlatforms(refresh!!)
    }
}