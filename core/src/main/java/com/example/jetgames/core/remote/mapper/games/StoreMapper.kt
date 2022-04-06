package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.remote.model.games.StoreDto
import com.example.jetgames.core.domain.model.games.Store
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class StoreMapper @Inject constructor(
    private val storeXMapper: StoreXMapper,
) : RemoteModelMapper<StoreDto, Store> {
    override fun mapFromModel(model: StoreDto?): Store {
        return Store(
            id = model?.id,
            storeX = storeXMapper.mapFromModel(model?.store)
        )
    }
}
