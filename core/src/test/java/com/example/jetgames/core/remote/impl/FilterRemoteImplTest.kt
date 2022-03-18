package com.example.jetgames.core.remote.impl

import com.example.jetgames.core.data.contract.FilterRemote
import com.example.jetgames.core.remote.model.genres.GenreDto
import com.example.jetgames.core.remote.model.genres.GenresResponse
import com.example.jetgames.core.remote.model.platforms.PlatformDto
import com.example.jetgames.core.remote.model.platforms.PlatformsResponse
import com.example.jetgames.core.remote.util.*
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class FilterRemoteImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var filterRemote: FilterRemote

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = RequestDispatcher()
        mockWebServer.start()
        filterRemote = FilterRemoteImpl(makeApiService(mockWebServer))
    }


    @Test
    fun `check that fetchPlatforms returns platforms list of same size`() = runBlocking {
        val platforms: List<PlatformDto> = filterRemote.fetchPlatforms()
        val responseSize = getResponseList(PLATFORMS_RESPONSE_PATH).sumOf {
            it.results.size
        }
        Truth.assertThat(platforms).isNotEmpty()
        Truth.assertThat(platforms.size).isEqualTo(responseSize)
    }

    @Test
    fun `check that calling fetchPlatforms make request to correct path`() = runBlocking {
        filterRemote.fetchPlatforms()
        Truth.assertThat("$PLATFORMS_PATH?key=$API_KEY")
            .isEqualTo(mockWebServer.takeRequest().path)
    }

    @Test
    fun `check that calling fetch platforms makes a GET request`() = runBlocking {
        filterRemote.fetchPlatforms()
        Truth.assertThat("GET $PLATFORMS_PATH?key=$API_KEY HTTP/1.1")
            .isEqualTo(mockWebServer.takeRequest().requestLine)
    }

    @Test
    fun `check that fetchPlatforms returns correct data`() {
        runBlocking {
            val response = getResponseList(PLATFORMS_RESPONSE_PATH).flatMap { it.results }
            val platforms = filterRemote.fetchPlatforms()
            Truth.assertThat(response).containsExactlyElementsIn(platforms)
        }
    }


    private fun getResponse(responsePath: String): PlatformsResponse {
        return platformsResponseAdapter.fromJson(getJson(responsePath))!!
    }

    private fun getResponseList(vararg responsePath: String): List<PlatformsResponse> {
        return responsePath.map(::getResponse)
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}