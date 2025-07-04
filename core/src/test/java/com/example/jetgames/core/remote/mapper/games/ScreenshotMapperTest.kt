package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class ScreenshotMapperTest {

    private val mapper = ScreenshotMapper()

    @Test
    fun mapFromModel() {
        val dto = DummyData.screenshot
        val domain = mapper.mapFromModel(dto)
        Truth.assertThat(dto.id).isEqualTo(domain.id)
    }
}
