package com.example.jetgames.favorites.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.common.ui.theme.JetgamesTheme

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
) {

    DefaultScreenUI {
        Text(
            text = "Favorites",
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h3)
    }
}

@Preview(showSystemUi = true)
@Composable
fun FavoritesPrev() {
    JetgamesTheme {
        FavoritesScreen()
    }
}