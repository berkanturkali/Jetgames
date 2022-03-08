package com.example.jetgames.core.remote.model.platforms

data class PlatformDto(
    val games: List<Game>,
    val games_count: Int,
    val id: Int,
    val image: Any,
    val image_background: String,
    val name: String,
    val slug: String,
    val year_end: Any,
    val year_start: Any
)