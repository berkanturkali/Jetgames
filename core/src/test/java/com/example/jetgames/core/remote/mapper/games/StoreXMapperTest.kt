package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.data.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class StoreXMapperTest{

    private val mapper = StoreXMapper()

    @Test
    fun mapFromModel(){
        val dto = DummyData.storeX
        val domain = mapper.mapFromModel(dto)
        Truth.assertThat(domain.count).isEqualTo(dto.games_count)
    }
}