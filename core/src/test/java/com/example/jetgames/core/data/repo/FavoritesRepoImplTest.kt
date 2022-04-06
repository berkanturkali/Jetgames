package com.example.jetgames.core.data.repo

import com.example.jetgames.core.cache.abstraction.FavoritesCache
import com.example.jetgames.core.cache.fakes.FakeFavoritesCache
import com.example.jetgames.core.cache.mapper.favorites.FavoriteEntityMapper
import com.example.jetgames.core.data.DummyData
import com.example.jetgames.core.domain.repo.FavoritesRepo
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FavoritesRepoImplTest {

    private val favoriteEntityMapper = FavoriteEntityMapper()

    private lateinit var cache: FavoritesCache

    private lateinit var repo: FavoritesRepo

    @Before
    fun setup() {
        cache = FakeFavoritesCache()
        repo =
            FavoritesRepoImpl(favoriteEntityMapper = favoriteEntityMapper, favoritesCache = cache)
    }

    @Test
    fun `check that upsert inserts data correctly`() {
        runBlocking {
            val domain = DummyData.favorite
            repo.upsert(domain)
            val favorites = cache.favorites().first()
            Truth.assertThat(favorites).isNotEmpty()
            Truth.assertThat(favorites.first().id).isEqualTo(domain.id)
        }
    }

    @Test
    fun `check that delete deletes data`() {
        runBlocking {
            val domain = DummyData.favorite
            repo.upsert(domain)
            val favs = cache.favorites().first()
            Truth.assertThat(favs).isNotEmpty()
            repo.delete(domain)
            val newFavs = cache.favorites().first()
            Truth.assertThat(newFavs.isEmpty())
        }
    }

    @Test
    fun `check that clear clears all of the data`() {
        runBlocking {
            val domain = DummyData.favorite
            val newDomain = domain.copy(id = 2)
            repo.upsert(domain)
            repo.upsert(newDomain)
            val favs = cache.favorites().first()
            Truth.assertThat(favs.size).isEqualTo(2)
            repo.clear()
            Truth.assertThat(cache.favorites().first()).isEmpty()
        }
    }
}
