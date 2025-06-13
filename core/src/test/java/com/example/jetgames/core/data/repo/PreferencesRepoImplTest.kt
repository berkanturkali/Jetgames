package com.example.jetgames.core.data.repo

import com.example.jetgames.core.DummyData
import com.example.jetgames.core.cache.abstraction.HomeFilterPreferencesCache
import com.example.jetgames.core.cache.fakes.FakeHomeFilterPreferencesCache
import com.example.jetgames.core.cache.mapper.platforms.PlatformEntityMapper
import com.example.jetgames.core.cache.mapper.preferences.HomeFilterPreferencesEntityMapper
import com.example.jetgames.core.domain.repo.PreferencesRepo
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class PreferencesRepoImplTest {

    private lateinit var preferencesCache: HomeFilterPreferencesCache

    private val platformMapper = PlatformEntityMapper()

    private val preferencesMapper =
        HomeFilterPreferencesEntityMapper(platformEntityMapper = platformMapper)

    private lateinit var repo: PreferencesRepo

    @Before
    fun setup() {
        preferencesCache = FakeHomeFilterPreferencesCache()
        repo = PreferencesRepoImpl(
            preferencesCache = preferencesCache,
            preferencesMapper = preferencesMapper
        )
    }

    @Test
    fun `check that insertPreferences inserts preferences as expected`() = runTest {
        val preferences = DummyData.filterPreferences
        repo.insertHomePreferences(preferences)
        Truth.assertThat(repo.preferences().first().platforms).isNotNull()
        Truth.assertThat(repo.preferences().first().platforms)
            .containsExactlyElementsIn(preferences.platforms)
    }

    @Test
    fun `check that preferences returns preferences as expected`() = runTest {
        val preferences = DummyData.filterPreferences
        Truth.assertThat(repo.preferences().first().platforms).isNull()
        repo.insertHomePreferences(preferences)
        Truth.assertThat(repo.preferences().first().platforms).isNotNull()
    }
}
