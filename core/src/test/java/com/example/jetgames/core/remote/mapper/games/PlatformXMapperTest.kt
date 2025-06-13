package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class PlatformXMapperTest {

    private val mapper = PlatformXMapper()

    @Test
    fun mapFromModel() {
        val dto = DummyData.platformX
        val domain = mapper.mapFromModel(dto)
        Truth.assertThat(dto.platform?.name).isEqualTo(domain.name)
    }
}
