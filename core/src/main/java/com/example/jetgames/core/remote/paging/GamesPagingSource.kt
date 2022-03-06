package com.example.jetgames.core.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetgames.core.data.contract.GamesRemote
import com.example.jetgames.core.remote.model.games.GameDto
import retrofit2.HttpException
import java.io.IOException

class GamesPagingSource constructor(
    private val gamesRemote: GamesRemote,
    private val query:String?
) : PagingSource<Int, GameDto>() {
    override fun getRefreshKey(state: PagingState<Int, GameDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameDto> {
        val page = params.key ?: 1
        val take = 20
        return try {
            val response = gamesRemote.fetchGames(page,take,query)
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (response.isEmpty()) null else page + 1
            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        }catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}