package com.example.jetgames.core.remote.model.genres

import com.squareup.moshi.Json

data class GenresResponse(
    @Json(name = "results")
    val genres: List<GenreDto>,
)