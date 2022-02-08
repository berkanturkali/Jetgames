package com.example.jetgames.core.remote.service

import com.example.jetgames.core.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun search(
        @Query("page")page:Int,
        @Query("page_size") size:Int,
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("ordering") ordering:String = "-metacritic"
    )
}