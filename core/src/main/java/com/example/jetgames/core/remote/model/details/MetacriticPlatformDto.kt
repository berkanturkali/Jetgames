package com.example.jetgames.core.remote.model.details

import com.example.jetgames.core.remote.model.games.PlatformDto

data class MetacriticPlatformDto(
    val metascore: Int,
    val platform: PlatformDto,
    val url: String,
)
