package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.remote.model.games.EsrbRatingDto
import com.example.jetgames.core.domain.model.games.EsrbRating
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class EsrbRatingMapper @Inject constructor() : RemoteModelMapper<EsrbRatingDto, EsrbRating> {
    override fun mapFromModel(model: EsrbRatingDto?): EsrbRating {
        return EsrbRating(
            id = model?.id,
            name = model?.name
        )
    }
}
