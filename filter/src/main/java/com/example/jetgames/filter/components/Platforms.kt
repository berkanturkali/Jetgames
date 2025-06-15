package com.example.jetgames.filter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.R.dimen
import com.example.jetgames.common.R.drawable
import com.example.jetgames.common.components.noRippleClickable
import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.navigation.PlatformsRoute

@Composable
fun Platforms(
    modifier: Modifier = Modifier,
    platforms: List<Platform>,
    onFilterItemClick: (String) -> Unit,
    onDeleteButtonClick: (Platform) -> Unit,
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    var expandable by rememberSaveable {
        mutableStateOf(false)
    }
    expandable = platforms.isNotEmpty()

    if (platforms.isEmpty()) {
        if (expanded) {
            expanded = false
        }
    }

    ExpandableItem(
        modifier = modifier,
        expanded = expanded,
        sectionTitle = "Platforms (${platforms.size})",
        screenRoute = PlatformsRoute::class.qualifiedName!!,
        onColumnClick = {
            if (expandable) {
                expanded = !expanded
            } else {
                onFilterItemClick(PlatformsRoute::class.qualifiedName!!)
            }
        },
        onSectionClick = onFilterItemClick
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = dimensionResource(
                    id =
                    dimen.dimen_16
                )
            )
        ) {
            platforms.forEach { platform ->
                Platform(platform = platform, onDeleteButtonClick = onDeleteButtonClick)
            }
        }
    }
}

@Composable
fun Platform(
    modifier: Modifier = Modifier,
    platform: Platform,
    onDeleteButtonClick: (Platform) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = platform.name!!,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onPrimary
        )

        Icon(
            painter = painterResource(id = drawable.ic_close),
            contentDescription = "Remove",
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .size(15.dp)
                .noRippleClickable {
                    onDeleteButtonClick.invoke(platform)
                }
        )
    }
}
