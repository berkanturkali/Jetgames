package com.example.jetgames.home.model

import com.example.jetgames.core.domain.model.games.GameModel

interface SeparatorGenerator {

    fun generateInitial(after: GameModel.GameItem): GameModel.SeparatorItem?
    fun generateBetween(
        before: GameModel.GameItem,
        after: GameModel.GameItem
    ): GameModel.SeparatorItem?

}