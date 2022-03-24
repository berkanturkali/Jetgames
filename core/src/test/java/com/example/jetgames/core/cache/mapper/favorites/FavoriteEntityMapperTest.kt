package com.example.jetgames.core.cache.mapper.favorites

import com.example.jetgames.core.data.DummyData
import com.google.common.truth.Truth
import org.junit.Test

class FavoriteEntityMapperTest {


    private val favoriteEntityMapper = FavoriteEntityMapper()


    @Test
    fun `check that map from entity maps data correctly`() {
        val entity = DummyData.favoriteEntity
        val domain = favoriteEntityMapper.mapFromEntity(entity = entity)
        Truth.assertThat(entity.id).isEqualTo(domain.id)
        Truth.assertThat(entity.name).isEqualTo(domain.name)
        Truth.assertThat(entity.image).isEqualTo(domain.image)
        Truth.assertThat(entity.metacritic).isEqualTo(domain.metacri)
        Truth.assertThat(entity.releaseDate).isEqualTo(domain.releaseDate)
    }

    @Test
    fun `check that map to entity maps data correctly`() {
        val domain = DummyData.favorite
        val entity = favoriteEntityMapper.mapToEntity(domain)
        Truth.assertThat(entity.id).isEqualTo(domain.id)
        Truth.assertThat(entity.name).isEqualTo(domain.name)
        Truth.assertThat(entity.image).isEqualTo(domain.image)
        Truth.assertThat(entity.metacritic).isEqualTo(domain.metacri)
        Truth.assertThat(entity.releaseDate).isEqualTo(domain.releaseDate)
    }

    @Test
    fun `check that map from entity maps data correctly when entity is null`() {
        val entity = null
        val domain = favoriteEntityMapper.mapFromEntity(entity = entity)
        Truth.assertThat(domain.id).isEqualTo(-1)
        Truth.assertThat(domain.name).isEqualTo("")
        Truth.assertThat(domain.releaseDate).isNull()
        Truth.assertThat(domain.metacri).isNull()
    }
}