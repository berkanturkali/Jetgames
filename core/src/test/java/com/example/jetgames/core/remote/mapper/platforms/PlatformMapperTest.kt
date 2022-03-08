package com.example.jetgames.core.remote.mapper.platforms

import com.example.jetgames.core.data.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class PlatformMapperTest {

    private val mapper = PlatformMapper()


    @Test
    fun `check that PlatformMapper maps data correctly`(){
      val domain = mapper.mapFromModel(DummyData.platformDto)
      Truth.assertThat(domain.id).isEqualTo(DummyData.platformDto.id)
      Truth.assertThat(domain.name).isEqualTo(DummyData.platformDto.name)
    }
}