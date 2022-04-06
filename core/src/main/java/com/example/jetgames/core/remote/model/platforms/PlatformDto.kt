package com.example.jetgames.core.remote.model.platforms

data class PlatformDto(
    val games: List<Game>? = null,
    val games_count: Int? = null,
    val id: Int? = null,
    val image: Any? = null,
    val image_background: String? = null,
    val name: String? = null,
    val slug: String? = null,
    val year_end: Any? = null,
    val year_start: Any? = null
)
