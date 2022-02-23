package com.example.jetgames.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.common.ui.theme.XXLightGray
import com.example.jetgames.core.domain.model.games.ParentPlatform
import com.example.jetgames.core.domain.model.games.Platform

@Composable
fun Platforms(
    modifier: Modifier = Modifier,
    platforms: List<ParentPlatform?>,
) {

    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.dimen_8))
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(text = stringResource(id = R.string.platforms),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onPrimary)

        Divider(thickness = 0.5.dp, color = XXLightGray)

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.dimen_16)),
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.dimen_16), Alignment.CenterHorizontally)) {
            val platformLogos = platformLogo(platforms = platforms)
            platformLogos.forEachIndexed { index, logo ->
                Column(horizontalAlignment = CenterHorizontally) {
                    Card(shape = CircleShape, modifier = Modifier
                        .size(60.dp)) {
                        PlatformLogoItem(platformImageResource = logo, modifier = Modifier.padding(
                            dimensionResource(id = R.dimen.dimen_16)))

                    }
                    Text(text = platforms[index]!!.platform!!.name!!,
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.Center)
                }
            }
        }
    }
}


@Preview
@Composable
fun PlatformsPrev() {
    JetgamesTheme {
        val platforms = listOf(ParentPlatform(Platform(id = 1, name = "playstation")),
            ParentPlatform(Platform(id = 2, name = "xbox")),
            ParentPlatform(Platform(id = 3, name = "pc")))
        Platforms(platforms = platforms)
    }
}