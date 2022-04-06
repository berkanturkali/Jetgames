package com.example.jetgames.core.remote.mapper.genres

import com.example.jetgames.core.cache.model.GenreEntity
import com.example.jetgames.core.remote.model.genres.GenreDto
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class GenreMapper @Inject constructor() : RemoteModelMapper<GenreDto, GenreEntity> {
    override fun mapFromModel(model: GenreDto?): GenreEntity {
        return GenreEntity(
            id = model!!.id,
            name = model.name
        )
    }
}
