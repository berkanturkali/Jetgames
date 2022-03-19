package com.example.jetgames.core.domain.model.preferences

import androidx.compose.ui.text.toLowerCase
import com.example.jetgames.core.domain.model.platforms.Platform
import java.util.*

sealed class HomePreferences {
    data class HomeViewPreferences(
        val isGalleryMode: Boolean = false,
    ) : HomePreferences()

    data class HomeFilterPreferences(
        val query: String? = null,
        val platforms: List<Platform> = emptyList(),
        val genres: List<String> = emptyList(),
    ) : HomePreferences() {

        fun mapPlatforms(): String? {
            return when {
                platforms.isEmpty() -> null
                platforms.size == 1 -> {
                    platforms[0].id.toString()
                }
                else -> {
                    platforms.map { platform -> platform.id }.joinToString(",")
                }
            }
        }

        fun mapGenres():String?{
            return when{
                genres.isEmpty() -> null
                genres.size == 1 ->{
                    genres[0].lowercase()
                }
                else->{
                    genres.joinToString(",").lowercase()
                }
            }
        }
    }
}