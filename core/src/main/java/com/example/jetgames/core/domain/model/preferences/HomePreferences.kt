package com.example.jetgames.core.domain.model.preferences

import com.example.jetgames.core.domain.model.platforms.Platform

sealed class HomePreferences {
    data class HomeViewPreferences(
        val isGalleryMode: Boolean = false,
    ) : HomePreferences()

    data class HomeFilterPreferences(
        val query: String? = null,
        val platforms: List<Platform> = emptyList(),
        val genres: List<String> = emptyList(),
        val metacriticPreference: MetacriticPreference = MetacriticPreference(),
        val order: OrderPreference = OrderPreference()
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

        fun mapGenres(): String? {
            return when {
                genres.isEmpty() -> null
                genres.size == 1 -> {
                    genres[0].lowercase()
                }
                else -> {
                    genres.joinToString(",").lowercase()
                }
            }
        }

        fun mapMetacritics(): String? {
            return when {
                metacriticPreference.min == 0 && metacriticPreference.max == 100 -> null
                else -> metacriticPreference.min.toString() + "," + metacriticPreference.max.toString()
            }
        }
    }
}
