package com.example.jetgames.core.remote.service

import com.example.jetgames.core.BuildConfig
import com.example.jetgames.core.remote.model.details.GameDetailsDto
import com.example.jetgames.core.remote.model.games.GamesResponse
import com.example.jetgames.core.remote.model.genres.GenresResponse
import com.example.jetgames.core.remote.model.platforms.PlatformsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun fetchGames(
        @Query("page") page: Int,
        @Query("page_size") size: Int,
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("ordering") ordering: String = "-metacritic",
        @Query("search") query: String?,
        @Query("search_exact") searchExact: Boolean = true,
        @Query("platforms") platforms:String? = null,
    ): GamesResponse

    @GET("games/{id}")
    suspend fun game(
        @Path("id") id: Int,
        @Query("key") key: String = BuildConfig.API_KEY,
    ): GameDetailsDto


    @GET("platforms")
    suspend fun fetchPlatforms(
        @Query("key") key: String = BuildConfig.API_KEY,
    ): PlatformsResponse

    @GET("genres")
    suspend fun fetchGenres(
        @Query("key") key: String = BuildConfig.API_KEY,
    ): GenresResponse
}