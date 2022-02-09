package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.data.DummyData
import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Test

class RatingMapperTest{

    private val mapper = RatingMapper()

    @Test
    fun mapFromModelTest(){
        val dto = DummyData.rating
        val domain = mapper.mapFromModel(dto)
        Truth.assertThat(domain.id).isEqualTo(dto.id)
    }

    @Test
    fun `check that icon is correct`(){
        val dto = DummyData.rating
        val domain = mapper.mapFromModel(dto)
        Truth.assertThat(domain.icon).isEqualTo("\uD83D\uDC4D")
    }
}