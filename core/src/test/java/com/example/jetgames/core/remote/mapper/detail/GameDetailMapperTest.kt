package com.example.jetgames.core.remote.mapper.detail

import com.example.jetgames.core.DummyData
import com.example.jetgames.core.remote.mapper.games.EsrbRatingMapper
import com.example.jetgames.core.remote.mapper.games.GenreMapper
import com.example.jetgames.core.remote.mapper.games.ParentPlatformMapper
import com.example.jetgames.core.remote.mapper.games.PlatformMapper
import com.example.jetgames.core.remote.mapper.games.RatingMapper
import com.example.jetgames.core.remote.mapper.games.StoreMapper
import com.example.jetgames.core.remote.mapper.games.StoreXMapper
import com.example.jetgames.core.remote.mapper.games.TagMapper
import com.google.common.truth.Truth
import org.junit.Test

class GameDetailMapperTest {

    private val esrbMapper = EsrbRatingMapper()

    private val genreMapper = GenreMapper()

    private val platformMapper = PlatformMapper()

    private val parentPlatformMapper = ParentPlatformMapper(platformMapper)

    private val ratingMapper = RatingMapper()

    private val tagMapper = TagMapper()

    private val storeXMapper = StoreXMapper()

    private val storeMapper = StoreMapper(storeXMapper = storeXMapper)

    private val metacriticPlatformMapper = MetacriticPlatformMapper(platformMapper)

    private val gameDetailMapper = GameDetailMapper(
        esrbRatingMapper = esrbMapper,
        genreMapper = genreMapper,
        metacriticPlatformMapper = metacriticPlatformMapper,
        parentPlatformMapper = parentPlatformMapper,
        ratingMapper = ratingMapper,
        storeMapper = storeMapper,
        tagMapper = tagMapper
    )

    @Test
    fun `check that mapFromModel map remote model correctly`() {
        val dto = DummyData.gameDetailsDto
        val domain = gameDetailMapper.mapFromModel(dto)
        Truth.assertThat(domain.id).isEqualTo(dto.id)
        Truth.assertThat(domain.name).isEqualTo(dto.name)
        Truth.assertThat(domain.description).isEqualTo(dto.description)
        Truth.assertThat(domain.esrb_rating).isEqualTo(dto.esrb_rating?.name)
        Truth.assertThat(domain.metacritic).isEqualTo(dto.metacritic)
    }
}
