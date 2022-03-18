package com.example.jetgames.core.remote.model.genres

data class Genre(
    val games: List<Game>,
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)