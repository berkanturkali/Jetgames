package com.example.jetgames.home.model

import com.example.jetgames.core.domain.model.games.Game
import com.example.jetgames.core.domain.model.games.GameModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class MetaCriticSeparatorGeneratorTest {

    @Test
    fun `initial separator for exact metaCritic`() {
        val after = gameItem(metaCritic = 85)
        val result = MetaCriticSeparatorGenerator.generateInitial(after)
        assertEquals(GameModel.SeparatorItem("85 Metascore"), result)
    }

    @Test
    fun `initial separator for non-boundary metaCritic`() {
        val after = gameItem(metaCritic = 89)
        val result = MetaCriticSeparatorGenerator.generateInitial(after)
        assertEquals(GameModel.SeparatorItem("85-89 Metascore"), result)
    }

    @Test
    fun `insert separator when after drops range`() {
        val before = gameItem(metaCritic = 89)
        val after = gameItem(metaCritic = 80)
        val result = MetaCriticSeparatorGenerator.generateBetween(before, after)
        assertEquals(GameModel.SeparatorItem("80-84 Metascore"), result)
    }

    @Test
    fun `no separator when same metaCritic range`() {
        val before = gameItem(metaCritic = 84)
        val after = gameItem(metaCritic = 83)
        val result = MetaCriticSeparatorGenerator.generateBetween(before, after)
        assertNull(result)
    }

    @Test
    fun `separator for after with no metaCritic`() {
        val before = gameItem(metaCritic = 82)
        val after = gameItem(metaCritic = null)
        val result = MetaCriticSeparatorGenerator.generateBetween(before, after)
        assertEquals(GameModel.SeparatorItem("No Metascore"), result)
    }
}

private fun gameItem(rating: Double? = null, metaCritic: Int? = null): GameModel.GameItem {
    return GameModel.GameItem(
        Game(
            rating = rating,
            metaCritic = metaCritic
        )
    )
}