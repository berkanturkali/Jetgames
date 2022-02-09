package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.remote.model.games.ShortScreenshotDto
import com.example.jetgames.core.domain.model.games.Screenshot
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class ScreenshotMapper @Inject constructor() : RemoteModelMapper<ShortScreenshotDto, Screenshot> {
    override fun mapFromModel(model: ShortScreenshotDto?): Screenshot {
        return Screenshot(
            id = model?.id,
            image = model?.image
        )
    }
}