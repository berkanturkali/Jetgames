package com.example.jetgames.favorites.state

import com.example.jetgames.core.domain.model.favorites.Favorite

data class FavoritesScreenState(
    val favorites: List<Favorite> = emptyList(),
    val showEmptyView: Boolean = true
)
