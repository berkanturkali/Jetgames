package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.data.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class ParentPlatformMapperTest {

    private val mapper = ParentPlatformMapper(PlatformMapper())

    @Test
    fun mapFromModel() {
        val dto = DummyData.parentPlatform
        val domain = mapper.mapFromModel(dto)
        Truth.assertThat(domain.platform?.name).isEqualTo(dto.platform?.name)
    }
}
