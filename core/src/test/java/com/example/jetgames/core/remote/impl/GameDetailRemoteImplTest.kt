package com.example.jetgames.core.remote.impl

import com.example.jetgames.core.data.contract.GameDetailRemote
import com.example.jetgames.core.remote.util.*
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class GameDetailRemoteImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var gameDetailRemote: GameDetailRemote

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = RequestDispatcher()
        mockWebServer.start()
        gameDetailRemote = GameDetailRemoteImpl(makeApiService(mockWebServer = mockWebServer))
    }

    @Test
    fun `check that game returns data`() = runBlocking {
        val game = gameDetailRemote.game(id = ID)
        Truth.assertThat(game.genres).isNotEmpty()
        Truth.assertThat(game.name).isEqualTo("Grand Theft Auto V")
    }

    @Test
    fun `check that calling game makes a GET request`() = runBlocking {
        gameDetailRemote.game(ID)
        Truth.assertThat("GET $REQUEST_PATH/$ID?key=$API_KEY HTTP/1.1")
            .isEqualTo(mockWebServer.takeRequest().requestLine)
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}