package com.example.jetgames.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.jetgames.common.R
import com.example.jetgames.core.domain.model.games.ParentPlatform

@Composable
fun PlatformLogoItem(platformImageResource: Int, modifier: Modifier = Modifier, size: Dp = 21.dp) {
    Image(
        modifier = modifier
            .size(size)
            .padding(horizontal = 1.dp),
        alignment = Alignment.Center,
        painter = rememberImagePainter(
            data = platformImageResource,
            builder = { crossfade(true) }
        ),
        contentDescription = null
    )
}

fun platformLogo(platforms: List<ParentPlatform?>): List<Int> {
    val platformLogosList = mutableListOf<Int>()

    platforms.forEach { platformItem ->
        val platformName = platformItem?.platform!!.name!!.lowercase()

        if (platformName.contains("pc") || platformName.contains("windows")) {
            platformLogosList.add(R.drawable.ic_windows)
        } else if (platformName.contains("xbox")) {
            platformLogosList.add(R.drawable.ic_xbox)
        } else if (platformName.contains("playstation")) {
            platformLogosList.add(R.drawable.ic_playstation)
        } else if (platformName.contains("linux")) {
            platformLogosList.add(R.drawable.ic_linux)
        } else if (platformName.contains("android")) {
            platformLogosList.add(R.drawable.ic_android)
        } else if (platformName.contains("apple") || platformName.contains("mac")) {
            platformLogosList.add(R.drawable.ic_apple)
        } else if (platformName.contains("nintendo")) {
            platformLogosList.add(R.drawable.ic_game)
        }
    }

    return platformLogosList.toSet().toList()
}
