package com.example.jetgames.core.domain.model.games

import androidx.compose.ui.graphics.Color


data class Game(
    val backgroundImage: String? = null,
    val esrbRating: EsrbRating? = null,
    val genres: List<Genre?>? = null,
    val id: Int? = null,
    val metaCritic: Int?= null,
    val name: String? = null,
    val parentPlatforms: List<ParentPlatform?>? = null,
    val platforms: List<PlatformX?>? = null,
    val rating: Double? = null,
    val rating_top: Int? = null,
    val ratings: List<Rating?>? = null,
    val ratingsCount: Int? = null,
    val released: String? = null,
    val reviewsCount: Int? = null,
    val reviewsTextCount: Int? = null,
    val screenShots: List<Screenshot?>? = null,
    val slug: String? = null,
    val stores: List<Store?>? = null,
    val tags: List<Tag?>? =  null,
) {
    fun calculateRgbFromRating(): Color? {
        return rating?.let {
            val intensity = 2 * (rating * 10).toInt()
            val percent = intensity * 0.01
            val red = (255 - (percent * 255)) * 2
            val green = (percent * 255 * 2)
            val blue = 0
            Color(minOf(255, red.toInt()), minOf(255, green.toInt()), blue)
        }
    }

    fun calculateRgbFromMetacritic():Color?{
        return metaCritic?.let {
            val percent = metaCritic * 0.01
            val red = (255 - (percent * 255)) * 2
            val green = (percent * 255 * 2)
            val blue = 0
            Color(minOf(255, red.toInt()), minOf(255, green.toInt()), blue)
        }
    }
}