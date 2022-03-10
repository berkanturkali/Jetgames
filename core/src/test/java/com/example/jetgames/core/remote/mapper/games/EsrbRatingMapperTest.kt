package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.data.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class EsrbRatingMapperTest{

   private val mapper = EsrbRatingMapper()

    @Test
    fun mapFromModel(){
        val dto = DummyData.esrbRating
        val domain = mapper.mapFromModel(dto)
        Truth.assertThat(domain.name).isEqualTo(dto.name)
    }


    @Test
    fun mapFromModelWithNull(){
        val domain = mapper.mapFromModel(null)
        Truth.assertThat(domain.name).isNull()
    }
}