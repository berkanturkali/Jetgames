package com.example.jetgames.core.cache.mapper.platforms

import com.example.jetgames.core.cache.mapper.base.EntityMapper
import com.example.jetgames.core.cache.model.PlatformEntity
import com.example.jetgames.core.domain.model.platforms.Platform
import javax.inject.Inject

class PlatformEntityMapper @Inject constructor() : EntityMapper<PlatformEntity, Platform> {
    override fun mapFromEntity(entity: PlatformEntity): Platform {
        return Platform(
            id = entity.id,
            name = entity.name
        )
    }

    override fun mapToEntity(type: Platform): PlatformEntity {
        return PlatformEntity(
            id = type.id ?: -1,
            name = type.name ?: ""
        )
    }
}
