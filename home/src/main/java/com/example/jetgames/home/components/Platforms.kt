package com.example.jetgames.home.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetgames.core.domain.model.games.ParentPlatform

@Composable
fun Platforms(
    modifier: Modifier = Modifier,
    platforms: List<ParentPlatform?>? = null,
) {
    if (platforms != null) {
        val platformLogos = platformLogo(platforms = platforms)

        LazyRow(modifier = modifier) {
            items(items = platformLogos) { platformLogo ->
                PlatformLogoItem(platformImageResource = platformLogo, size = 21.dp)
            }
        }
    }
}
