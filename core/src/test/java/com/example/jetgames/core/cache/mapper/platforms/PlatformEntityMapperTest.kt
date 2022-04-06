package com.example.jetgames.core.cache.mapper.platforms

import com.example.jetgames.core.data.DummyData
import com.example.jetgames.core.domain.model.platforms.Platform
import com.google.common.truth.Truth
import org.junit.Test

class PlatformEntityMapperTest {

    private val mapper = PlatformEntityMapper()

    @Test
    fun `check that mapToEntity maps correctly`() {
        val domain = Platform(
            id = 3,
            name = "Playstation 3"
        )
        val entity = mapper.mapToEntity(domain)
        Truth.assertThat(entity.id).isEqualTo(domain.id)
        Truth.assertThat(entity.name).isEqualTo(domain.name)
    }

    @Test
    fun `check that mapToEntity maps the null fields correctly`() {
        val domain = Platform(
            id = null,
            name = ""
        )
        val entity = mapper.mapToEntity(domain)
        Truth.assertThat(entity.id).isEqualTo(-1)
        Truth.assertThat(entity.name).isEqualTo("")
    }

    @Test
    fun `check that mapFromEntity maps correctly`() {
        val entity = DummyData.platformEntity
        val domain = mapper.mapFromEntity(entity = entity)
        Truth.assertThat(domain.id).isEqualTo(entity.id)
        Truth.assertThat(domain.name).isEqualTo(entity.name)
    }
}
