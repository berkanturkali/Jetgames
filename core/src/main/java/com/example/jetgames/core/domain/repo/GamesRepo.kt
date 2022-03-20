package com.example.jetgames.core.domain.repo

import androidx.paging.PagingData
import com.example.jetgames.core.domain.model.games.Game
import com.example.jetgames.core.domain.model.platforms.Platform
import kotlinx.coroutines.flow.Flow

interface GamesRepo {

    fun fetchGames(
        query: String?,
        platforms: String?,
        genres:String?,
        metacritic:String?,
    ): Flow<PagingData<Game>>
}