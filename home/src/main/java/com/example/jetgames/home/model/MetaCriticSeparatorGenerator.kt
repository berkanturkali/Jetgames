package com.example.jetgames.home.model

import com.example.jetgames.core.domain.model.games.GameModel
import com.example.jetgames.core.domain.model.games.lowerBound
import com.example.jetgames.core.domain.model.games.upper

object MetaCriticSeparatorGenerator : SeparatorGenerator {
    override fun generateInitial(after: GameModel.GameItem): GameModel.SeparatorItem? {
        return after.game.metaCritic?.let { meta ->
            val lower = after.lowerBound()
            val label = if (lower == meta) {
                "$lower Metascore"
            } else {
                "$lower-$meta Metascore"
            }
            GameModel.SeparatorItem(label)
        }
    }

    override fun generateBetween(
        before: GameModel.GameItem,
        after: GameModel.GameItem
    ): GameModel.SeparatorItem? {
        if(before.game.metaCritic == null) return null
        val afterMeta = after.game.metaCritic ?: return GameModel.SeparatorItem("No Metascore")

        val lower = before.lowerBound()

        return if (afterMeta < lower) {
            val range = if (afterMeta + 1 != lower) {
                val upper = after.upper()
                "${upper - 5}-${upper - 1}"
            } else {
                "${lower - 5}-${lower - 1}"
            }
            GameModel.SeparatorItem("$range Metascore")
        } else {
            null
        }
    }
}