package com.example.jetgames.core.cache.mapper.preferences

import com.example.jetgames.core.cache.mapper.platforms.PlatformEntityMapper
import com.example.jetgames.core.data.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class HomeFilterPreferencesEntityMapperTest {

    private val platformEntityMapper = PlatformEntityMapper()

    private val mapper = HomeFilterPreferencesEntityMapper(platformEntityMapper = platformEntityMapper)


    @Test
    fun `check that mapToEntity maps to entity correctly`() {
        val domain = DummyData.filterPreferences
        val entity = mapper.mapToEntity(domain)
        Truth.assertThat(entity.platforms!!.first().name).isEqualTo(domain.platforms.first().name)
        Truth.assertThat(entity.platforms!!.first().id).isEqualTo(domain.platforms.first().id)
        Truth.assertThat(entity.minMetacri).isEqualTo(domain.metacriticPreference.min)
        Truth.assertThat(entity.maxMetacri).isEqualTo(domain.metacriticPreference.max)
    }

    @Test
    fun `check that mapFromEntity maps to domain correctly`(){
        val entity = DummyData.filterPreferencesEntity
        val domain = mapper.mapFromEntity(entity = entity)
        Truth.assertThat(entity.platforms!!.first().name).isEqualTo(domain.platforms.first().name)
        Truth.assertThat(entity.platforms!!.first().id).isEqualTo(domain.platforms.first().id)
        Truth.assertThat(entity.minMetacri).isEqualTo(domain.metacriticPreference.min)
        Truth.assertThat(entity.maxMetacri).isEqualTo(domain.metacriticPreference.max)
        val nullEntity = null
        val newDomain = mapper.mapFromEntity(nullEntity)
        Truth.assertThat(newDomain.platforms).isEmpty()
        Truth.assertThat(newDomain.metacriticPreference.min).isEqualTo(0)
        Truth.assertThat(newDomain.metacriticPreference.max).isEqualTo(100)
    }
}