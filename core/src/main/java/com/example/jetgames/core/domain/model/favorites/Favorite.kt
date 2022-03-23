package com.example.jetgames.core.domain.model.favorites

data class Favorite(
    val id: Int,
    val image: String?,
    val name: String,
    val rating: Double?,
    val metacri: Int?,
    val releaseDate: String?,
)