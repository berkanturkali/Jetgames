package com.example.jetgames.core.remote.model.games

data class GamesResponse(
    val count:Int,
    val results:List<GameDto>
)