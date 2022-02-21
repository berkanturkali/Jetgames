package com.example.jetgames.core.remote.mapper.detail

import com.example.jetgames.core.domain.model.detail.MetacriticPlatform
import com.example.jetgames.core.remote.mapper.games.PlatformMapper
import com.example.jetgames.core.remote.model.details.MetacriticPlatformDto
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class MetacriticPlatformMapper @Inject constructor(
    private val platformMapper: PlatformMapper,
) : RemoteModelMapper<MetacriticPlatformDto, MetacriticPlatform> {
    override fun mapFromModel(model: MetacriticPlatformDto?): MetacriticPlatform {
        return MetacriticPlatform(
            metascore = model?.metascore ?: -1,
            platform = platformMapper.mapFromModel(model?.platform)
        )
    }
}