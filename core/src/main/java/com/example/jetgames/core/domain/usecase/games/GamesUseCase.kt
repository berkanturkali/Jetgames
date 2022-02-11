package com.example.jetgames.core.domain.usecase.games

import androidx.paging.PagingData
import com.example.jetgames.core.domain.executor.abstraction.PostExecutionThread
import com.example.jetgames.core.domain.model.games.Game
import com.example.jetgames.core.domain.repo.GamesRepo
import com.example.jetgames.core.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GamesUseCase @Inject constructor(
    private val executionThread: PostExecutionThread,
    private val repo: GamesRepo,
) : FlowUseCase<Unit, PagingData<Game>>() {

    override val dispatcher: CoroutineDispatcher
        get() = executionThread.main

    override fun execute(params: Unit?): Flow<PagingData<Game>> {
        return repo.fetchGames()
    }
}