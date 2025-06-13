package com.example.jetgames.core.remote.mapper.detail

import com.example.jetgames.core.DummyData
import com.example.jetgames.core.remote.mapper.games.PlatformMapper
import com.google.common.truth.Truth
import org.junit.Test

class MetacriticPlatformMapperTest {

    private val platformMapper = PlatformMapper()

    private val metacriticPlatformMapper = MetacriticPlatformMapper(platformMapper = platformMapper)

    @Test
    fun `check that mapFromModel map data correctly`() {
        val dto = DummyData.metacriticPlaform
        val domain = metacriticPlatformMapper.mapFromModel(dto)
        Truth.assertThat(domain.metascore).isEqualTo(dto.metascore)
    }

    @Test
    fun `check that when dto is null metacritic of the domain object is -1`() {
        val domain = metacriticPlatformMapper.mapFromModel(null)
        Truth.assertThat(domain.metascore).isEqualTo(-1)
    }
}
