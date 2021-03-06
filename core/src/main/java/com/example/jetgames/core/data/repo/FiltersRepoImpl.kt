package com.example.jetgames.core.data.repo

import com.example.jetgames.core.cache.abstraction.GenresCache
import com.example.jetgames.core.cache.abstraction.PlatformsCache
import com.example.jetgames.core.cache.mapper.genres.GenreEntityMapper
import com.example.jetgames.core.cache.mapper.platforms.PlatformEntityMapper
import com.example.jetgames.core.cache.model.PlatformEntity
import com.example.jetgames.core.data.contract.FilterRemote
import com.example.jetgames.core.domain.model.genres.Genre
import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.core.domain.repo.FiltersRepo
import com.example.jetgames.core.domain.util.Resource
import com.example.jetgames.core.remote.mapper.genres.GenreMapper
import com.example.jetgames.core.remote.mapper.platforms.PlatformMapper
import com.example.jetgames.core.remote.model.platforms.PlatformDto
import com.example.jetgames.core.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FiltersRepoImpl @Inject constructor(
    private val platformMapper: PlatformMapper,
    private val platformEntityMapper: PlatformEntityMapper,
    private val platformsCache: PlatformsCache,
    private val filterRemote: FilterRemote,
    private val genresCache: GenresCache,
    private val genreMapper: GenreMapper,
    private val genreEntityMapper: GenreEntityMapper

) : FiltersRepo {
    override fun fetchPlatforms(refresh: Boolean): Flow<Resource<List<Platform>>> {
        return networkBoundResource<List<PlatformDto>, List<PlatformEntity>, List<Platform>>(
            dbQuery = { platformsCache.platforms() },
            apiCall = { filterRemote.fetchPlatforms() },
            saveFetchResult = { platforms ->
                platformsCache.insertAll(platformMapper.mapModelList(platforms))
            },
            onFetchFailed = { throwable ->
            },
            mapFromEntity = {
                platformEntityMapper.mapTypeList(it)!!
            },
            shouldFetch = {
                it.isNullOrEmpty()
            },
            refresh = refresh
        )
    }

    override fun fetchGenres(refresh: Boolean): Flow<Resource<List<Genre>>> {
        return networkBoundResource(
            dbQuery = { genresCache.genres() },
            apiCall = { filterRemote.fetchGenres() },
            saveFetchResult = { genres ->
                genresCache.insertAll(genreMapper.mapModelList(genres))
            },
            onFetchFailed = { throwable ->
            },
            mapFromEntity = {
                genreEntityMapper.mapTypeList(it)!!
            },
            shouldFetch = {
                it.isNullOrEmpty()
            },
            refresh = refresh
        )
    }
}
