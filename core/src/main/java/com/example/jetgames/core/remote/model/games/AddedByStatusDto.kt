package com.example.jetgames.core.remote.model.games

data class AddedByStatusDto(
    val beaten: Int?,
    val dropped: Int?,
    val owned: Int?,
    val playing: Int?,
    val toplay: Int?,
    val yet: Int?
)