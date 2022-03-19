package com.example.jetgames.filter.state

import com.example.jetgames.core.domain.model.genres.Genre
import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.core.domain.model.preferences.MetacriticPreference

data class FilterState(
    val selectedPlatforms: List<Platform>? = null,
    val selectedGenres: List<String>? = null,
    val selectedMetacritics: MetacriticPreference? = null,
    val isApplyButtonVisible: Boolean = false,
)