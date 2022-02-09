package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.remote.model.games.ParentPlatformDto
import com.example.jetgames.core.domain.model.games.ParentPlatform
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class ParentPlatformMapper @Inject constructor(
    private val platformMapper: PlatformMapper,
) : RemoteModelMapper<ParentPlatformDto, ParentPlatform> {
    override fun mapFromModel(model: ParentPlatformDto?): ParentPlatform {
        return ParentPlatform(
            platform = platformMapper.mapFromModel(model?.platform)
        )
    }
}