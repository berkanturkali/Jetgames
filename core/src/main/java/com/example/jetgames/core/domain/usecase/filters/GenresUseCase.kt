package com.example.jetgames.core.domain.usecase.filters

import com.example.jetgames.core.domain.executor.abstraction.PostExecutionThread
import com.example.jetgames.core.domain.model.genres.Genre
import com.example.jetgames.core.domain.repo.FiltersRepo
import com.example.jetgames.core.domain.usecase.base.FlowUseCase
import com.example.jetgames.core.domain.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenresUseCase @Inject constructor(
    private val executionThread: PostExecutionThread,
    private val repo: FiltersRepo,
) : FlowUseCase<Boolean, Resource<List<Genre>>>() {
    override val dispatcher: CoroutineDispatcher
        get() = executionThread.io

    override fun execute(params: Boolean?): Flow<Resource<List<Genre>>> {
        return repo.fetchGenres(params!!)
    }
}