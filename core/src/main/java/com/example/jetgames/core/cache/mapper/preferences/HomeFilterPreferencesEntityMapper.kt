package com.example.jetgames.core.cache.mapper.preferences

import com.example.jetgames.core.cache.mapper.base.EntityMapper
import com.example.jetgames.core.cache.mapper.platforms.PlatformEntityMapper
import com.example.jetgames.core.cache.model.HomeFilterPreferencesEntity
import com.example.jetgames.core.domain.model.preferences.HomePreferences
import com.example.jetgames.core.domain.model.preferences.MetacriticPreference
import javax.inject.Inject

class HomeFilterPreferencesEntityMapper @Inject constructor(
    private val platformEntityMapper: PlatformEntityMapper,
) : EntityMapper<HomeFilterPreferencesEntity?, HomePreferences.HomeFilterPreferences> {
    override fun mapFromEntity(entity: HomeFilterPreferencesEntity?): HomePreferences.HomeFilterPreferences {
        return HomePreferences.HomeFilterPreferences(
            platforms = platformEntityMapper.mapTypeList(entity?.platforms) ?: emptyList(),
            genres = entity?.genres ?: emptyList(),
            metacriticPreference = MetacriticPreference(min = entity?.minMetacri ?: 0,
                max = entity?.maxMetacri ?: 100)
        )
    }

    override fun mapToEntity(type: HomePreferences.HomeFilterPreferences): HomeFilterPreferencesEntity {
        return HomeFilterPreferencesEntity(
            platforms = platformEntityMapper.mapToEntityList(type.platforms),
            genres = type.genres,
            minMetacri = type.metacriticPreference.min,
            maxMetacri = type.metacriticPreference.max
        )
    }
}