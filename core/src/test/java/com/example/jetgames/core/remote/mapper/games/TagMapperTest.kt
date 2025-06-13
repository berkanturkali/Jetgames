package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class TagMapperTest {

    private val mapper = TagMapper()

    @Test
    fun mapFromModel() {
        val dto = DummyData.tag
        val domain = mapper.mapFromModel(dto)
        Truth.assertThat(domain.count).isEqualTo(dto.games_count)
    }
}
