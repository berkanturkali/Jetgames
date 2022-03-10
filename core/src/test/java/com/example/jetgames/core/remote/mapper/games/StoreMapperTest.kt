package com.example.jetgames.core.remote.mapper.games

import com.example.jetgames.core.data.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class StoreMapperTest{

    private val mapper = StoreMapper(StoreXMapper())

    @Test
    fun mapFromModel(){
        val dto = DummyData.store
        val domain = mapper.mapFromModel(dto)
        Truth.assertThat(domain.id).isEqualTo(dto.id)
    }
}