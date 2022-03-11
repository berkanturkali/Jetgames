package com.example.jetgames.filter.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.filter.viewmodel.PlatformsScreenViewModel

@Composable
fun PlatformItem(
    modifier: Modifier = Modifier,
    platform: Platform,
    viewModel: PlatformsScreenViewModel = hiltViewModel(),
) {
    var selected by rememberSaveable {
        mutableStateOf(false)
    }

    Row(modifier = modifier
        .fillMaxWidth()
        .height(40.dp)
        .clickable {
            selected = !selected
        },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.dimen_8)),
            text = platform.name!!,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary)

        RadioButton(selected = selected, onClick = {
            selected = !selected
        })

    }
}

@Preview
@Composable
fun PlatformItemPrev() {
    JetgamesTheme {
        PlatformItem(platform = Platform(5, "Playstation 5"))
    }
}