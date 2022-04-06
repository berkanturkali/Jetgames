package com.example.jetgames.core.remote.impl

import com.example.jetgames.core.data.contract.GameDetailRemote
import com.example.jetgames.core.remote.model.details.GameDetailsDto
import com.example.jetgames.core.remote.service.ApiService
import javax.inject.Inject

class GameDetailRemoteImpl @Inject constructor(
    private val apiService: ApiService,
) : GameDetailRemote {
    override suspend fun game(id: Int): GameDetailsDto {
        return apiService.game(id)
    }
}
