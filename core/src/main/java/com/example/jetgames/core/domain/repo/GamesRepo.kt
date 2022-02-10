package com.example.jetgames.core.domain.repo

import androidx.paging.PagingData
import com.example.jetgames.core.domain.model.games.Game
import kotlinx.coroutines.flow.Flow

interface GamesRepo {

    fun fetchGames(): Flow<PagingData<Game>>
}