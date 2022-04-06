package com.example.jetgames.core.data.repo

import com.example.jetgames.core.cache.abstraction.GenresCache
import com.example.jetgames.core.cache.abstraction.PlatformsCache
import com.example.jetgames.core.cache.fakes.FakeGenresCache
import com.example.jetgames.core.cache.fakes.FakePlatformsCache
import com.example.jetgames.core.cache.mapper.genres.GenreEntityMapper
import com.example.jetgames.core.cache.mapper.platforms.PlatformEntityMapper
import com.example.jetgames.core.data.DummyData
import com.example.jetgames.core.data.contract.FilterRemote
import com.example.jetgames.core.domain.repo.FiltersRepo
import com.example.jetgames.core.domain.util.Resource
import com.example.jetgames.core.remote.mapper.genres.GenreMapper
import com.example.jetgames.core.remote.mapper.platforms.PlatformMapper
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

class FiltersRepoImplTest {

    private val platformMapper = PlatformMapper()

    private val platformEntityMapper = PlatformEntityMapper()

    private val genreMapper = GenreMapper()

    private val genreEntityMapper = GenreEntityMapper()

    private lateinit var genresCache: GenresCache

    private lateinit var platformsCache: PlatformsCache

    private val mockFilterRemote = mockk<FilterRemote>(relaxed = true)

    private lateinit var repo: FiltersRepo

    @Before
    fun setup() {
        platformsCache = FakePlatformsCache()
        genresCache = FakeGenresCache()
        repo = FiltersRepoImpl(
            platformMapper = platformMapper,
            platformEntityMapper = platformEntityMapper,
            platformsCache = platformsCache,
            filterRemote = mockFilterRemote,
            genresCache = genresCache,
            genreMapper = genreMapper,
            genreEntityMapper = genreEntityMapper
        )
    }

    @Test
    fun `check that fetchPlatforms emits initially loading object if cache is empty`() {
        runTest {
            coEvery { mockFilterRemote.fetchPlatforms() } returns listOf(DummyData.platformDto)
            val value = repo.fetchPlatforms(true).first()
            Truth.assertThat(value).isInstanceOf(Resource.Loading::class.java)
        }
    }

    @Test
    fun `check that fetchPlatforms saves data into cache then load from cache if cache is empty`() {
        runTest {
            coEvery { mockFilterRemote.fetchPlatforms() } returns listOf(DummyData.platformDto)
            val value = repo.fetchPlatforms(true).toList()[1]
            Truth.assertThat(value).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(value.data).isNotNull()
            Truth.assertThat(value.data).isNotEmpty()
            Truth.assertThat(value.data)
                .containsExactlyElementsIn(
                    platformEntityMapper.mapTypeList(
                        platformMapper.mapModelList(
                            listOf(DummyData.platformDto)
                        )
                    )
                )
        }
    }

    @Test
    fun `check that fetchPlatforms returns error if error occures`() {
        runTest {
            coEvery { mockFilterRemote.fetchPlatforms() } throws IOException()
            val value = repo.fetchPlatforms(true).toList()[1]
            Truth.assertThat(value).isInstanceOf(Resource.Error::class.java)
        }
    }

    @Test
    fun `check that fetchPlatforms loads data from cache if cache is not empty or null`() {
        runTest {
            coEvery { mockFilterRemote.fetchPlatforms() } returns listOf(DummyData.platformDto)
            platformsCache.insertAll(listOf(DummyData.platformEntity))
            val value = repo.fetchPlatforms(true).toList()[0]
            coVerify(exactly = 0) { mockFilterRemote.fetchPlatforms() }
            Truth.assertThat(value).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(value.data).isNotNull()
            Truth.assertThat(value.data).isNotEmpty()
            Truth.assertThat(value.data)
                .containsExactlyElementsIn(platformEntityMapper.mapTypeList(listOf(DummyData.platformEntity)))
        }
    }

    @Test
    fun `check that fetchGenres emits initially loading object if cache is empty`() {
        runTest {
            coEvery { mockFilterRemote.fetchGenres() }.returns(listOf(DummyData.genreDto))
            val value = repo.fetchGenres(true).first()
            Truth.assertThat(value).isInstanceOf(Resource.Loading::class.java)
        }
    }

    @Test
    fun `check that fetchGenres fetch data from network and saves data into cache then returns it if the cache is empty`() {
        runTest {
            coEvery { mockFilterRemote.fetchGenres() }.returns(listOf(DummyData.genreDto))
            val value = repo.fetchGenres(true).toList()
            Truth.assertThat(value.get(0)).isInstanceOf(Resource.Loading::class.java)
            Truth.assertThat(value.get(1)).isInstanceOf(Resource.Success::class.java)
            coVerify(exactly = 1) { mockFilterRemote.fetchGenres() }
            Truth.assertThat(value.get(1).data).isNotEmpty()
            Truth.assertThat(value.get(1).data)
                .containsExactlyElementsIn(genreEntityMapper.mapTypeList(listOf(DummyData.genreEntity)))
        }
    }

    @Test
    fun `check that fetchGenres loads data directly from cache if cache is not empty`() {
        runTest {
            coEvery { mockFilterRemote.fetchGenres() } returns (listOf(DummyData.genreDto))
            genresCache.insertAll(listOf(DummyData.genreEntity))
            val value = repo.fetchGenres(true).first()
            coVerify(exactly = 0) { mockFilterRemote.fetchGenres() }
            Truth.assertThat(value).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(value.data).isNotEmpty()
            Truth.assertThat(value.data).containsExactlyElementsIn(
                genreEntityMapper.mapTypeList(
                    listOf(DummyData.genreEntity)
                )
            )
        }
    }

    @Test
    fun `check that fetchGenres returns error object if error occurs while fetching data`() {
        runTest {
            coEvery { mockFilterRemote.fetchGenres() } throws IOException()
            val value = repo.fetchGenres(true).toList()
            Truth.assertThat(value.get(0)).isInstanceOf(Resource.Loading::class.java)
            Truth.assertThat(value.get(1)).isInstanceOf(Resource.Error::class.java)
        }
    }
}
