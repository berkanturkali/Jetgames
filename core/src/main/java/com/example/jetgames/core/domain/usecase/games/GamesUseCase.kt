package com.example.jetgames.core.domain.usecase.games

import androidx.paging.PagingData
import com.example.jetgames.core.domain.executor.abstraction.PostExecutionThread
import com.example.jetgames.core.domain.model.games.Game
import com.example.jetgames.core.domain.model.preferences.HomePreferences
import com.example.jetgames.core.domain.repo.GamesRepo
import com.example.jetgames.core.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class GamesUseCase @Inject constructor(
    private val executionThread: PostExecutionThread,
    private val repo: GamesRepo,
) : FlowUseCase<HomePreferences.HomeFilterPreferences, PagingData<Game>>() {

    override val dispatcher: CoroutineDispatcher
        get() = executionThread.io


    override fun execute(params: HomePreferences.HomeFilterPreferences?): Flow<PagingData<Game>> {
        val platforms = params!!.mapPlatforms()
        val genres = params.mapGenres()
        val metacri = params.mapMetacritics()
        val order = params.order.mapOrderToString()
        return repo.fetchGames(params.query,
            platforms = platforms,
            genres = genres,
            metacritic = metacri,
            order = order
            )
    }
}