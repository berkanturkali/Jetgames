package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.remote.model.games.StoreXDto
import com.example.jetgames.core.domain.model.games.StoreX
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class StoreXMapper @Inject constructor() : RemoteModelMapper<StoreXDto, StoreX> {
    override fun mapFromModel(model: StoreXDto?): StoreX {
        return StoreX(
            domain = model?.domain,
            count = model?.games_count,
            id = model?.id,
            background = model?.image_background,
            name = model?.name
        )
    }
}
