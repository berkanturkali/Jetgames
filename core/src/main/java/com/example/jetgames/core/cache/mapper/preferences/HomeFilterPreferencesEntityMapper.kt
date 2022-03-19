package com.example.jetgames.core.cache.mapper.preferences

import com.example.jetgames.core.cache.mapper.base.EntityMapper
import com.example.jetgames.core.cache.mapper.platforms.PlatformEntityMapper
import com.example.jetgames.core.cache.model.HomeFilterPreferencesEntity
import com.example.jetgames.core.domain.model.preferences.HomePreferences
import com.example.jetgames.core.domain.model.preferences.Order
import com.example.jetgames.core.domain.model.preferences.OrderPreference
import javax.inject.Inject

class HomeFilterPreferencesEntityMapper @Inject constructor(
    private val platformEntityMapper: PlatformEntityMapper,
) : EntityMapper<HomeFilterPreferencesEntity?, HomePreferences.HomeFilterPreferences> {
    override fun mapFromEntity(entity: HomeFilterPreferencesEntity?): HomePreferences.HomeFilterPreferences {
        return HomePreferences.HomeFilterPreferences(
            platforms = platformEntityMapper.mapTypeList(entity?.platforms) ?: emptyList(),
            genres = entity?.genres ?: emptyList()
        )
    }

    override fun mapToEntity(type: HomePreferences.HomeFilterPreferences): HomeFilterPreferencesEntity {
        return HomeFilterPreferencesEntity(
            platforms = platformEntityMapper.mapToEntityList(type.platforms),
            genres = type.genres
        )
    }
}