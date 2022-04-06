package com.example.jetgames.details.state

import com.example.jetgames.core.domain.model.detail.GameDetails
import com.example.jetgames.core.domain.util.Resource

data class DetailsScreenState(
    val game: Resource<GameDetails>? = null,
    val isLiked: Boolean = false,
    val screenShots: List<String?> = emptyList(),
)
