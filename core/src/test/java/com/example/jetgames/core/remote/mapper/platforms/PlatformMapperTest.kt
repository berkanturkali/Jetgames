package com.example.jetgames.core.remote.mapper.platforms

import com.example.jetgames.core.data.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class PlatformMapperTest {

    private val mapper = PlatformMapper()


    @Test
    fun `check that PlatformMapper maps data correctly`(){
      val entity = mapper.mapFromModel(DummyData.platformDto)
      Truth.assertThat(entity.id).isEqualTo(DummyData.platformDto.id)
      Truth.assertThat(entity.name).isEqualTo(DummyData.platformDto.name)
    }
}