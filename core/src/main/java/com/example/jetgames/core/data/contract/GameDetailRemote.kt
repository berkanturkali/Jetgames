package com.example.jetgames.core.data.contract

import com.example.jetgames.core.remote.model.details.GameDetailsDto

interface GameDetailRemote {

    suspend fun game(id:Int):GameDetailsDto
}