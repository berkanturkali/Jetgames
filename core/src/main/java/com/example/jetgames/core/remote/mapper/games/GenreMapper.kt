package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.remote.model.games.GenreDto
import com.example.jetgames.core.domain.model.games.Genre
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class GenreMapper @Inject constructor() : RemoteModelMapper<GenreDto, Genre> {
    override fun mapFromModel(model: GenreDto?): Genre {
        return Genre(
            count = model?.games_count,
            id = model?.id,
            background = model?.image_background,
            name = model?.name
        )
    }
}
