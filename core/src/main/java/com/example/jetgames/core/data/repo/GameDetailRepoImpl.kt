package com.example.jetgames.core.data.repo

import com.example.jetgames.core.data.contract.GameDetailRemote
import com.example.jetgames.core.domain.model.detail.GameDetails
import com.example.jetgames.core.domain.repo.GameDetailRepo
import com.example.jetgames.core.remote.mapper.detail.GameDetailMapper
import javax.inject.Inject

class GameDetailRepoImpl @Inject constructor(
    private val gameDetailRemote: GameDetailRemote,
    private val detailMapper: GameDetailMapper
) : GameDetailRepo {
    override suspend fun game(id: Int): GameDetails {
        return detailMapper.mapFromModel(gameDetailRemote.game(id))
    }
}
