package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class PlatformMapperTest {

    private val mapper = PlatformMapper()

    @Test
    fun mapFromModel() {
        val dto = DummyData.platform
        val domain = mapper.mapFromModel(
            dto
        )
        Truth.assertThat(dto.name).isEqualTo(domain.name)
    }
}
