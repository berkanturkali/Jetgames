package com.example.jetgames.core.cache.implementation

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetgames.core.cache.abstraction.GenresCache
import com.example.jetgames.core.cache.db.JetgamesDb
import com.example.jetgames.core.data.DummyData
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GenresCacheImplTest {

    private lateinit var jetgamesDb: JetgamesDb

    private lateinit var genresCache: GenresCache

    @Before
    fun setup() {
        jetgamesDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            JetgamesDb::class.java
        )
            .allowMainThreadQueries().build()

        genresCache = GenresCacheImpl(jetgamesDb.genresDao)
    }

    @Test
    fun upsert_insertsData_successfully() {
        runBlocking {
            val entity = DummyData.genreEntity
            genresCache.upsert(entity)
            val result = genresCache.genres().first().first()
            Truth.assertThat(result.id).isEqualTo(entity.id)
            Truth.assertThat(result.name).isEqualTo(entity.name)
        }
    }

    @Test
    fun upsert_updatesData_when_dataAlreadyInDb() = runBlocking {
        val entity = DummyData.genreEntity
        genresCache.upsert(entity)
        val result = genresCache.genres().first().first()
        Truth.assertThat(result.id).isEqualTo(entity.id)
        Truth.assertThat(result.name).isEqualTo(entity.name)
        val newEntity = entity.copy(name = "indie")
        genresCache.upsert(newEntity)
        val newResult = genresCache.genres().first().first()
        Truth.assertThat(newResult.id).isEqualTo(newEntity.id)
        Truth.assertThat(newResult.name).isEqualTo(newEntity.name)
    }

    @Test
    fun insertAll_insertsAllData_successfully() {
        runBlocking {
            val entities = listOf(DummyData.genreEntity)
            genresCache.insertAll(entities)
            val result = genresCache.genres().first()
            Truth.assertThat(result).isNotEmpty()
            Truth.assertThat(result.size).isEqualTo(entities.size)
            Truth.assertThat(result).containsExactlyElementsIn(entities)
        }
    }

    @Test
    fun clear_clearAllData_successfully() {
        runBlocking {
            val entities = listOf(DummyData.genreEntity)
            genresCache.insertAll(entities)
            val result = genresCache.genres().first()
            Truth.assertThat(result).isNotEmpty()
            genresCache.clear()
            val newResult = genresCache.genres().first()
            Truth.assertThat(newResult).isEmpty()
        }
    }

    @Test
    fun genres_returnsEmptyList_when_dbIsEmpty() {
        runBlocking {
            val result = genresCache.genres().first()
            Truth.assertThat(result).isEmpty()
        }
    }

    @After
    fun tearDown() {
        jetgamesDb.close()
    }
}
