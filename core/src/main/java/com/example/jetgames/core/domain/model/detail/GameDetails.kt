package com.example.jetgames.core.domain.model.detail

import androidx.compose.ui.graphics.Color
import com.example.jetgames.core.utils.calculateRgbFromMetacritic
import com.example.jetgames.core.utils.calculateRgbFromRating
import com.example.jetgames.core.domain.model.games.*

data class GameDetails(
    val background_image: String?,
    val description: String?,
    val esrb_rating: String?,
    val genres: List<Genre?>?,
    val id: Int?,
    val metacritic: Int?,
    val metacritic_platforms: List<MetacriticPlatform?>?,
    val name: String?,
    val parent_platforms: List<ParentPlatform?>?,
    val rating: Double?,
    val rating_top: Int?,
    val ratings: List<Rating?>?,
    val ratings_count: Int?,
    val released: String?,
    val stores: List<Store?>?,
    val tags: List<Tag?>?,
    val website: String?,
) {

    fun calculateRgbFromRating(): Color? {
        return rating?.calculateRgbFromRating()
    }


    fun calculateRgbFromMetacritic(): Color? {
        return metacritic?.calculateRgbFromMetacritic()
    }
}