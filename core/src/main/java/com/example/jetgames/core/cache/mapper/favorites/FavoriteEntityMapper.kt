package com.example.jetgames.core.cache.mapper.favorites

import com.example.jetgames.core.cache.mapper.base.EntityMapper
import com.example.jetgames.core.cache.model.FavoriteEntity
import com.example.jetgames.core.domain.model.favorites.Favorite
import javax.inject.Inject

class FavoriteEntityMapper @Inject constructor() : EntityMapper<FavoriteEntity?, Favorite> {
    override fun mapFromEntity(entity: FavoriteEntity?): Favorite {
        return Favorite(
            id = entity?.id ?: -1,
            name = entity?.name ?: "",
            rating = entity?.rating,
            releaseDate = entity?.releaseDate,
            image = entity?.image,
            metacri = entity?.metacritic,
            icon = entity?.icon
        )
    }

    override fun mapToEntity(type: Favorite): FavoriteEntity {
        return FavoriteEntity(
            id = type.id,
            name = type.name,
            rating = type.rating,
            releaseDate = type.releaseDate,
            image = type.image,
            metacritic = type.metacri,
            icon = type.icon
        )
    }
}