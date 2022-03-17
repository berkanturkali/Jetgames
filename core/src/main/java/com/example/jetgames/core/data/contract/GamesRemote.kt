package com.example.jetgames.core.data.contract

import com.example.jetgames.core.remote.model.games.GameDto

interface GamesRemote {
    suspend fun fetchGames(
        page: Int,
        size: Int,
        query: String?,
        platforms: String?,
    ): List<GameDto>
}