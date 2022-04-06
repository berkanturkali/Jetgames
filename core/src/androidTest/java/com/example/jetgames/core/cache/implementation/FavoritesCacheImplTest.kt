package com.example.jetgames.core.cache.implementation

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetgames.core.cache.abstraction.FavoritesCache
import com.example.jetgames.core.cache.db.JetgamesDb
import com.example.jetgames.core.cache.model.FavoriteEntity
import com.example.jetgames.core.data.DummyData
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoritesCacheImplTest {

    private lateinit var jetgamesDb: JetgamesDb

    private lateinit var favoritesCache: FavoritesCache

    @Before
    fun setup() {
        jetgamesDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            JetgamesDb::class.java
        )
            .allowMainThreadQueries().build()

        favoritesCache = FavoritesCacheImpl(jetgamesDb.favoritesDao)
    }

    @Test
    fun upsert_insertsData_when_dataIsNotInCache() {
        runBlocking {
            val entity = DummyData.favoriteEntity
            val entities = favoritesCache.favorites().first()
            Truth.assertThat(entities).isEmpty()
            favoritesCache.upsert(entity)
            val newEntities = favoritesCache.favorites().first()
            Truth.assertThat(newEntities).isNotNull()
            Truth.assertThat(newEntities.size).isEqualTo(1)
            Truth.assertThat(newEntities.first().id).isEqualTo(entity.id)
        }
    }

    @Test
    fun upsert_updatesData_when_dataIsAlreadyInCache() {
        runBlocking {
            val entity = DummyData.favoriteEntity
            favoritesCache.upsert(entity)
            val entities = favoritesCache.favorites().first()
            Truth.assertThat(entities).isNotEmpty()
            val newEntity = entity.copy(name = "Dummy")
            favoritesCache.upsert(newEntity)
            val newData = favoritesCache.favorites().first()
            Truth.assertThat(newData).isNotEmpty()
            Truth.assertThat(newData.first().id).isEqualTo(entity.id)
            Truth.assertThat(newData.first().name).isNotEqualTo(entity.name)
        }
    }

    @Test
    fun delete_deletesData_correctly() {
        runBlocking {
            val entity = DummyData.favoriteEntity
            favoritesCache.upsert(entity)
            Truth.assertThat(favoritesCache.favorites().first()).isNotEmpty()
            favoritesCache.delete(entity)
            Truth.assertThat(favoritesCache.favorites().first()).isEmpty()
        }
    }

    @Test
    fun clear_clearsAllData_correctly() {
        runBlocking {
            val entity = DummyData.favoriteEntity
            val newEntity = FavoriteEntity(
                id = 2,
                name = "Red Dead Redemption 2",
                image = "",
                releaseDate = "12 Nov 2019",
                rating = 5.0,
                metacritic = 100
            )
            favoritesCache.upsert(entity)
            favoritesCache.upsert(newEntity)
            Truth.assertThat(favoritesCache.favorites().first().size).isEqualTo(2)
            favoritesCache.clear()
            Truth.assertThat(favoritesCache.favorites().first()).isEmpty()
        }
    }

    @Test
    fun favorite_getsData_correctly() {
        runBlocking {
            val entity = favoritesCache.favorite(1)
            Truth.assertThat(entity).isNull()
            val newEntity = DummyData.favoriteEntity
            favoritesCache.upsert(newEntity)
            val new = favoritesCache.favorite(newEntity.id)
            Truth.assertThat(new).isNotNull()
        }
    }

    @After
    fun tearDown() {
        jetgamesDb.close()
    }
}
