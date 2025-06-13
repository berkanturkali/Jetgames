package com.example.jetgames.core.cache.implementation

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetgames.core.cache.DummyData
import com.example.jetgames.core.cache.abstraction.PlatformsCache
import com.example.jetgames.core.cache.db.JetgamesDb
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlatformsCacheImplTest {

    private lateinit var jetGamesDb: JetgamesDb

    private lateinit var platformsCache: PlatformsCache

    @Before
    fun setup() {
        jetGamesDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            JetgamesDb::class.java
        )
            .allowMainThreadQueries().build()
        platformsCache = PlatformsCacheImpl(jetGamesDb.platformsDao)
    }

    @Test
    fun upsert_insertsData_successfully() = runBlocking {
        val entity = DummyData.platformEntity
        platformsCache.upsert(entity)
        val result = platformsCache.platforms().first().first()
        Truth.assertThat(result.id).isEqualTo(entity.id)
        Truth.assertThat(result.name).isEqualTo(entity.name)
    }

    @Test
    fun upsert_updatesData_when_dataAlreadyInDb() = runBlocking {
        val entity = DummyData.platformEntity
        platformsCache.upsert(entity)
        val newEntity = entity.copy(name = "pc")
        platformsCache.upsert(newEntity)
        val result = platformsCache.platforms().first().first()
        Truth.assertThat(result.name).isEqualTo(newEntity.name)
    }

    @Test
    fun insertAll_insertsAllData_successfully() {
        runBlocking {
            val entities = listOf(DummyData.platformEntity)
            platformsCache.insertAll(entities)
            val result = platformsCache.platforms().first()
            Truth.assertThat(result).isNotEmpty()
            Truth.assertThat(result.size).isEqualTo(entities.size)
            Truth.assertThat(result).containsExactlyElementsIn(entities)
        }
    }

    @Test
    fun clear_clearAllData_successfully() {
        runBlocking {
            val entities = listOf(DummyData.platformEntity)
            platformsCache.insertAll(entities)
            val result = platformsCache.platforms().first()
            Truth.assertThat(result).isNotEmpty()
            platformsCache.clear()
            val newResult = platformsCache.platforms().first()
            Truth.assertThat(newResult).isEmpty()
        }
    }

    @After
    fun tearDown() {
        jetGamesDb.close()
    }
}
