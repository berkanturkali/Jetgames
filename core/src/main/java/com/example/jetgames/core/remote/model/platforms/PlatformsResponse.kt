package com.example.jetgames.core.remote.model.platforms

data class PlatformsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PlatformDto>
)