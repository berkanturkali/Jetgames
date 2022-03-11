package com.example.jetgames.core.remote.mapper.platforms

import com.example.jetgames.core.cache.model.PlatformEntity
import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.core.remote.model.platforms.PlatformDto
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class PlatformMapper @Inject constructor():RemoteModelMapper<PlatformDto,PlatformEntity> {
    override fun mapFromModel(model: PlatformDto?): PlatformEntity {
        return PlatformEntity(
            id = model!!.id!!,
            name = model.name!!
        )
    }
}