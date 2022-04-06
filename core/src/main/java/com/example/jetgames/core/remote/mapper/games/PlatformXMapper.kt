package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.remote.model.games.PlatformXDto
import com.example.jetgames.core.domain.model.games.PlatformX
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class PlatformXMapper @Inject constructor() : RemoteModelMapper<PlatformXDto, PlatformX> {
    override fun mapFromModel(model: PlatformXDto?): PlatformX {
        return PlatformX(
            count = model?.platform?.games_count,
            id = model?.platform?.id,
            background = model?.platform?.image_background,
            name = model?.platform?.name,
            releasedAt = model?.released_at
        )
    }
}
