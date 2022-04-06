package com.example.jetgames.core.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.jetgames.core.data.contract.GamesRemote
import com.example.jetgames.core.domain.model.games.Game
import com.example.jetgames.core.domain.repo.GamesRepo
import com.example.jetgames.core.remote.mapper.games.GameMapper
import com.example.jetgames.core.remote.paging.GamesPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GamesRepoImpl @Inject constructor(
    private val gamesRemote: GamesRemote,
    private val gameMapper: GameMapper,
) : GamesRepo {
    override fun fetchGames(
        query: String?,
        platforms: String?,
        genres: String?,
        metacritic: String?,
        order: String,
    ): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                GamesPagingSource(
                    gamesRemote = gamesRemote,
                    query,
                    platforms = platforms,
                    genres = genres,
                    metacritic = metacritic,
                    order = order
                )
            }
        ).flow.map {
            it.map(gameMapper::mapFromModel)
        }
    }
}
