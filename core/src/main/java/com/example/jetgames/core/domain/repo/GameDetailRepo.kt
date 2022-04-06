package com.example.jetgames.core.domain.repo

import com.example.jetgames.core.domain.model.detail.GameDetails

interface GameDetailRepo {
    suspend fun game(id: Int): GameDetails
}
