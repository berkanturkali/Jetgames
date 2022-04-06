package com.example.jetgames.filter.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.R
import com.example.jetgames.core.domain.model.platforms.Platform

@Composable
fun PlatformItem(
    modifier: Modifier = Modifier,
    flag: Boolean,
    platform: Platform,
    onItemSelected: (Platform, Boolean) -> Unit,
) {
    var checked by rememberSaveable {
        mutableStateOf(flag)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.dimen_8)),
            text = platform.name!!,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary
        )
        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it
                onItemSelected(platform, checked)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.secondary,
                checkmarkColor = MaterialTheme.colors.onPrimary
            ),
        )
    }
}
