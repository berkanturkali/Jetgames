package com.example.jetgames.core.cache.implementation

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetgames.core.cache.DummyData
import com.example.jetgames.core.cache.abstraction.HomeFilterPreferencesCache
import com.example.jetgames.core.cache.db.JetgamesDb
import com.example.jetgames.core.cache.model.PlatformEntity
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFilterPreferencesCacheImplTest {

    private lateinit var jetGamesDb: JetgamesDb

    private lateinit var cache: HomeFilterPreferencesCache

    @Before
    fun setup() {
        jetGamesDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            JetgamesDb::class.java
        )
            .allowMainThreadQueries().build()
        cache = HomeFilterPreferencesCacheImpl(jetGamesDb.homeFilterPreferencesDao)
    }

    @Test
    fun upsert_insertsEntityIntoDb_when_EntityIsNotAlreadyThere() {
        runBlocking {
            val entity = DummyData.filterPreferencesEntity
            Truth.assertThat(cache.preferences().first()).isNull()
            cache.upsert(entity)
            val prefs = cache.preferences().first()
            Truth.assertThat(prefs).isNotNull()
            Truth.assertThat(prefs.platforms).containsExactlyElementsIn(entity.platforms)
        }
    }

    @Test
    fun upsert_updatesEntity_when_EntityIsAlreadyInCache() {
        runBlocking {
            val entity = DummyData.filterPreferencesEntity
            cache.upsert(entity)
            val prefs = cache.preferences().first()
            Truth.assertThat(prefs).isNotNull()
            Truth.assertThat(prefs.platforms).containsExactlyElementsIn(entity.platforms)
            val newPlatforms = entity.platforms!! + PlatformEntity(name = "Playstation 4", id = 4)
            val newEntity = DummyData.filterPreferencesEntity.copy(platforms = newPlatforms)
            cache.upsert(newEntity)
            val newPrefs = cache.preferences().first()
            Truth.assertThat(newPrefs).isNotNull()
            Truth.assertThat(newPrefs.platforms!!).containsExactlyElementsIn(newPlatforms)
        }
    }

    @After
    fun tearDown() {
        jetGamesDb.close()
    }
}
