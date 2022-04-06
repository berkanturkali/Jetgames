package com.example.jetgames.core.domain.repo

import androidx.paging.PagingData
import com.example.jetgames.core.domain.model.games.Game
import kotlinx.coroutines.flow.Flow

interface GamesRepo {

    fun fetchGames(
        query: String?,
        platforms: String?,
        genres: String?,
        metacritic: String?,
        order: String
    ): Flow<PagingData<Game>>
}
