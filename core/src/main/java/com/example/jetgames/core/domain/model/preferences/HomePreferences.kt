package com.example.jetgames.core.domain.model.preferences

import com.example.jetgames.core.domain.model.platforms.Platform

sealed class HomePreferences {
    data class HomeViewPreferences(
        val isGalleryMode: Boolean = false,
    ) : HomePreferences()

    data class HomeFilterPreferences(
        val query: String? = null,
        val platforms: List<Platform>? = null,
    ) : HomePreferences(){

        fun mapPlatforms(): String? {
            return platforms?.let {
                when {
                    it.isEmpty() -> null
                    it.size == 1 -> {
                        it[0].id.toString()
                    }
                    else -> {
                        it.map { platform -> platform.id }.joinToString(", ")
                    }
                }
            }
        }
    }
}