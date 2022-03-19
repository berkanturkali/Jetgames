package com.example.jetgames.core.remote.impl

import com.example.jetgames.core.data.contract.GamesRemote
import com.example.jetgames.core.remote.model.games.GameDto
import com.example.jetgames.core.remote.service.ApiService
import javax.inject.Inject

class GamesRemoteImpl @Inject constructor(
    private val apiService: ApiService,
) : GamesRemote {
    override suspend fun fetchGames(
        page: Int,
        size: Int,
        query: String?,
        platforms: String?,
        genres: String?,
    ): List<GameDto> {
        return apiService.fetchGames(page,
            size,
            query = query,
            platforms = platforms,
            genres = genres).results
    }
}