package com.example.jetgames.core.domain.model.games.preferences

sealed class HomePreferences {
    data class HomeViewPreferences(
        val isGalleryMode:Boolean = false,
    ): HomePreferences()
    data class HomeFilterPreferences(
        val query:String? = null,
    ): HomePreferences()
}