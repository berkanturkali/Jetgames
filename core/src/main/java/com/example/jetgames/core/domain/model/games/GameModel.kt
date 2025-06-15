package com.example.jetgames.core.domain.model.games

sealed class GameModel {
    data class GameItem(val game: Game) : GameModel()
    data class SeparatorItem(val separator: String) : GameModel()
}

fun GameModel.GameItem.lowerBound(): Int {
    return this.game.metaCritic!! - (this.game.metaCritic % 5)
}

fun GameModel.GameItem.upper(): Int {
    return this.game.metaCritic!! + (this.game.metaCritic % 5)
}
