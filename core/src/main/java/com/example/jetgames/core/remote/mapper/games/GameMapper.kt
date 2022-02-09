package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.domain.model.games.Game
import com.example.jetgames.core.remote.model.games.GameDto
import com.example.jetgames.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class GameMapper @Inject constructor(
    private val esrbRatingMapper: EsrbRatingMapper,
    private val genreMapper: GenreMapper,
    private val parentPlatformMapper: ParentPlatformMapper,
    private val platformXMapper: PlatformXMapper,
    private val ratingMapper: RatingMapper,
    private val screenshotMapper: ScreenshotMapper,
    private val storeMapper: StoreMapper,
    private val tagMapper: TagMapper,
) : RemoteModelMapper<GameDto, Game> {

    override fun mapFromModel(model: GameDto?): Game {
        return Game(
            backgroundImage = model?.background_image,
            esrbRating = esrbRatingMapper.mapFromModel(model?.esrb_rating),
            genres = genreMapper.mapModelList(model?.genres),
            id = model?.id,
            metaCritic = model?.metacritic,
            name = model?.name,
            parentPlatforms = parentPlatformMapper.mapModelList(model?.parent_platforms),
            platforms = platformXMapper.mapModelList(model?.platforms),
            rating = model?.rating,
            rating_top = model?.rating_top,
            ratings = ratingMapper.mapModelList(model?.ratings),
            ratingsCount = model?.ratings_count,
            released = model?.released,
            reviewsCount = model?.reviews_count,
            reviewsTextCount = model?.reviews_text_count,
            screenShots = screenshotMapper.mapModelList(model?.short_screenshots),
            slug = model?.slug,
            stores = storeMapper.mapModelList(model?.stores),
            tags = tagMapper.mapModelList(model?.tags)
        )
    }
}