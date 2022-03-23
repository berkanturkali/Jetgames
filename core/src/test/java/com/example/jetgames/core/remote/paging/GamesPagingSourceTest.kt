package com.example.jetgames.core.remote.paging

import androidx.paging.PagingSource
import com.example.jetgames.core.data.contract.GamesRemote
import com.example.jetgames.core.remote.impl.GamesRemoteImpl
import com.example.jetgames.core.remote.util.*
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GamesPagingSourceTest {

    private lateinit var mockServer: MockWebServer
    private lateinit var gamesRemote: GamesRemote

    @Before
    fun setup() {
        mockServer = MockWebServer()
        mockServer.dispatcher = RequestDispatcher()
        mockServer.start()
        gamesRemote = GamesRemoteImpl(makeApiService(mockServer))
    }

    @Test
    fun `check that prev and next keys are correct`() = runBlocking {
        val pagingSource = GamesPagingSource(gamesRemote, null, null, null, null, order = ORDERING)
        val games = gamesRemote.fetchGames(PAGE, SIZE, null, null, null, null, order = ORDERING)
        Truth.assertThat(
            PagingSource.LoadResult.Page(
                data = games,
                prevKey = null,
                nextKey = 2
            )
        ).isEqualTo(pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = SIZE,
                placeholdersEnabled = false
            )
        ))
    }

    @Test
    fun `check that if error occurs returns Error`() = runBlocking {
        val gamesRemote = mockk<GamesRemote>()
        coEvery { gamesRemote.fetchGames(PAGE, SIZE, null, null, null, null,order = ORDERING) } throws IOException()
        val pagingSource = GamesPagingSource(gamesRemote, null, null, null, null,order = ORDERING)
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )
        Truth.assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

}