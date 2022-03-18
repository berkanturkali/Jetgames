package com.example.jetgames.core.remote.model.genres

data class GenresResponse(
    val count: Int,
    val next: Any?,
    val previous: Any?,
    val genres: List<Genre>
)