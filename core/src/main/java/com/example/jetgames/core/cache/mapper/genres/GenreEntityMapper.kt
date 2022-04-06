package com.example.jetgames.core.cache.mapper.genres

import com.example.jetgames.core.cache.mapper.base.EntityMapper
import com.example.jetgames.core.cache.model.GenreEntity
import com.example.jetgames.core.domain.model.genres.Genre
import javax.inject.Inject

class GenreEntityMapper @Inject constructor() : EntityMapper<GenreEntity, Genre> {
    override fun mapFromEntity(entity: GenreEntity): Genre {
        return Genre(
            id = entity.id,
            name = entity.name
        )
    }

    override fun mapToEntity(type: Genre): GenreEntity {
        return GenreEntity(
            id = type.id,
            name = type.name
        )
    }
}
