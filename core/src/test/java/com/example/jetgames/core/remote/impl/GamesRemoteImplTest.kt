package com.example.jetgames.core.remote.impl

import com.example.jetgames.core.data.contract.GamesRemote
import com.example.jetgames.core.remote.model.games.GameDto
import com.example.jetgames.core.remote.model.games.GamesResponse
import com.example.jetgames.core.remote.util.*
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class GamesRemoteImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var gamesRemote: GamesRemote

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = RequestDispatcher()
        mockWebServer.start()
        gamesRemote = GamesRemoteImpl(makeApiService(mockWebServer))
    }

    @Test
    fun `check that fetchGames returns game list of same size`() = runBlocking {
        val games: List<GameDto> = gamesRemote.fetchGames(1, 20, null)
        val responseSize = getResponseList(GAMES_RESPONSE_PATH).sumOf {
            it.results.size
        }
        Truth.assertThat(games).isNotEmpty()
        Truth.assertThat(games.size).isEqualTo(responseSize)
    }

    @Test
    fun `check that calling fetchGames make request to correct path`() = runBlocking {
        gamesRemote.fetchGames(1, 20,null)
        Truth.assertThat("$REQUEST_PATH?page=$PAGE&page_size=$SIZE&key=$API_KEY&ordering=$ORDERING")
            .isEqualTo(mockWebServer.takeRequest().path)
    }

    @Test
    fun `check that calling fetch games makes a GET request`() = runBlocking {
        gamesRemote.fetchGames(PAGE, SIZE, null)
        Truth.assertThat("GET $REQUEST_PATH?page=$PAGE&page_size=$SIZE&key=$API_KEY&ordering=$ORDERING HTTP/1.1")
            .isEqualTo(mockWebServer.takeRequest().requestLine)
    }

    @Test
    fun `check that fetchGames returns correct data`() {
        runBlocking {
            val response = getResponseList(GAMES_RESPONSE_PATH).flatMap { it.results }
            val games = gamesRemote.fetchGames(PAGE, SIZE, null)
            Truth.assertThat(response).containsExactlyElementsIn(games)
        }
    }

    @Test
    fun `check that fetchGames returns correct data with query`() {
        runBlocking {
            val response = getResponseList(SEARCH_RESPONSE_PATH).flatMap { it.results }
            val games = gamesRemote.fetchGames(PAGE, SIZE, SEARCH_QUERY)
            Truth.assertThat(response).containsExactlyElementsIn(games)
        }
    }

    @Test
    fun `check that fetchGames returns empty list when no game is found`() =
        runBlocking {
            val games = gamesRemote.fetchGames(PAGE,SIZE, NO_MATCH_SEARCH_QUERY)
            Truth.assertThat(games).isEmpty()
        }


    private fun getResponse(responsePath: String): GamesResponse {
        return gamesResponseAdapter.fromJson(getJson(responsePath))!!
    }

    private fun getResponseList(vararg responsePath: String): List<GamesResponse> {
        return responsePath.map(::getResponse)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}