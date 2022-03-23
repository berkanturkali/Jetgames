package com.example.jetgames.core.domain.model.games

import androidx.compose.ui.graphics.Color
import com.example.jetgames.core.utils.calculateRgbFromMetacritic
import com.example.jetgames.core.utils.calculateRgbFromRating


data class Game(
    val backgroundImage: String? = null,
    val esrbRating: EsrbRating? = null,
    val genres: List<Genre?>? = null,
    val id: Int? = null,
    val metaCritic: Int? = null,
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
    val tags: List<Tag?>? = null,
) {
    fun calculateRgbFromRating(): Color? {
        return rating?.calculateRgbFromRating()
    }

    fun calculateRgbFromMetacritic(): Color? {
        return metaCritic?.calculateRgbFromMetacritic()
    }
}