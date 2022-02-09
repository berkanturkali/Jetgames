package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.data.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class GameMapperTest {

    private val esrbMapper = EsrbRatingMapper()

    private val genreMapper = GenreMapper()

    private val platformMapper = PlatformMapper()

    private val parentPlatformMapper = ParentPlatformMapper(platformMapper)

    private val platformXMapper = PlatformXMapper()

    private val ratingMapper = RatingMapper()

    private val screenshotMapper = ScreenshotMapper()

    private val tagMapper = TagMapper()

    private val storeXMapper = StoreXMapper()

    private val storeMapper = StoreMapper(storeXMapper = storeXMapper)


    val gameMapper = GameMapper(
        esrbRatingMapper = esrbMapper,
        genreMapper = genreMapper,
        parentPlatformMapper = parentPlatformMapper,
        platformXMapper = platformXMapper,
        ratingMapper = ratingMapper,
        screenshotMapper = screenshotMapper,
        tagMapper = tagMapper,
        storeMapper = storeMapper
    )

    @Test
    fun mapFromModel(){
        val dto = DummyData.game
        val domain = gameMapper.mapFromModel(dto)
        Truth.assertThat(dto.id).isEqualTo(domain.id)
    }
}