package com.example.jetgames.core.domain.model.detail

import androidx.compose.ui.graphics.Color
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
){

    fun calculateRgbFromRating(): Color? {
        return rating?.let {
            val intensity = 2 * (rating * 10).toInt()
            val percent = intensity * 0.01
            val red = 255
            val green = (percent * 255 * 2)
            val blue = 0
            Color(red, minOf(255, green.toInt()), blue)
        }
    }

    fun calculateRgbFromMetacritic(): Color?{
        return metacritic?.let {
            val percent = metacritic * 0.01
            val red = (255 - (percent * 255)) * 2
            val green = (percent * 255 * 2)
            val blue = 0
            Color(minOf(255, red.toInt()), minOf(255, green.toInt()), blue)
        }
    }
}