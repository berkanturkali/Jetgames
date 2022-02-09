package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.remote.model.games.TagDto
import com.example.jetgames.core.domain.model.games.Tag
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class TagMapper @Inject constructor() : RemoteModelMapper<TagDto, Tag> {
    override fun mapFromModel(model: TagDto?): Tag {
        return Tag(
            count = model?.games_count,
            id = model?.id,
            background = model?.image_background,
            name = model?.name
        )
    }
}