package com.example.jetgames.core.domain.model.detail

import com.example.jetgames.core.domain.model.games.*

data class GameDetails(
    val background_image: String,
    val description: String,
    val description_raw: String,
    val esrb_rating: String,
    val genres: List<Genre>,
    val id: Int,
    val metacritic: Int,
    val metacritic_platforms: List<MetacriticPlatform>,
    val name: String,
    val parent_platforms: List<ParentPlatform>,
    val rating: Double,
    val rating_top: Int,
    val ratings: List<Rating>,
    val ratings_count: Int,
    val released: String,
    val stores: List<Store>,
    val tags: List<Tag>,
    val website: String,
)