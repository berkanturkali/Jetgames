package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.remote.model.games.RatingDto
import com.example.jetgames.core.domain.model.games.Rating
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class RatingMapper @Inject constructor() : RemoteModelMapper<RatingDto, Rating> {
    override fun mapFromModel(model: RatingDto?): Rating {
        return Rating(
            count = model?.count,
            id = model?.id,
            percent = model?.percent,
            title = model?.title,
            icon = setIcon(model?.title)
        )
    }

    private fun setIcon(title: String?): String? {
        return when (title) {
            "exceptional" -> "\uD83C\uDFAF"
            "recommended" -> "\uD83D\uDC4D"
            "skip" -> "\u26D4"
            "meh" -> "\uD83D\uDE11"
            else -> null
        }
    }
}
