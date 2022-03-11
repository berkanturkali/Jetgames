package com.example.jetgames.core.remote.mapper.detail

import com.example.jetgames.core.domain.model.detail.GameDetails
import com.example.jetgames.core.remote.mapper.games.*
import com.example.jetgames.core.remote.model.details.GameDetailsDto
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class GameDetailMapper @Inject constructor(
    private val esrbRatingMapper: EsrbRatingMapper,
    private val genreMapper: GenreMapper,
    private val metacriticPlatformMapper: MetacriticPlatformMapper,
    private val parentPlatformMapper: ParentPlatformMapper,
    private val ratingMapper: RatingMapper,
    private val storeMapper: StoreMapper,
    private val tagMapper: TagMapper,
) : RemoteModelMapper<GameDetailsDto, GameDetails> {
    override fun mapFromModel(model: GameDetailsDto?): GameDetails {
        return GameDetails(
            background_image = model?.background_image,
            description = model?.description_raw,
            esrb_rating = esrbRatingMapper.mapFromModel(model?.esrb_rating).name,
            genres = genreMapper.mapModelList(model?.genres ?: emptyList()),
            id = model?.id,
            metacritic = model?.metacritic,
            metacritic_platforms = metacriticPlatformMapper.mapModelList(model?.metacritic_platforms ?: emptyList()),
            name = model?.name,
            parent_platforms = parentPlatformMapper.mapModelList(model?.parent_platforms ?: emptyList()),
            rating = model?.rating,
            rating_top = model?.rating_top,
            ratings = ratingMapper.mapModelList(model?.ratings ?: emptyList()),
            ratings_count = model?.ratings_count,
            released = model?.released,
            stores = storeMapper.mapModelList(model?.stores ?: emptyList()),
            tags = tagMapper.mapModelList(model?.tags ?: emptyList()),
            website = model?.website
        )
    }
}