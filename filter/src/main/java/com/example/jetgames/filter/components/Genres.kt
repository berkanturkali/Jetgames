package com.example.jetgames.filter.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.jetgames.common.R
import com.example.jetgames.navigation.GenresRoute

@Composable
fun Genres(
    modifier: Modifier = Modifier,
    genres: List<String>,
    onGenresItemClick: (String) -> Unit = {},
    onDeleteButtonClick: (String) -> Unit = {},
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    var expandable by rememberSaveable {
        mutableStateOf(false)
    }

    expandable = genres.isNotEmpty()

    if (genres.isEmpty()) {
        if (expanded) {
            expanded = false
        }
    }
    ExpandableItem(
        modifier = modifier,
        expanded = expanded,
        sectionTitle = "Genres (${genres.size})",
        onColumnClick = {
            if (expandable) {
                expanded = !expanded
            } else {
                onGenresItemClick(GenresRoute::class.qualifiedName!!)
            }
        },
        onSectionClick = onGenresItemClick,
        screenRoute = GenresRoute::class.qualifiedName!!
    ) {
        Column(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_16))) {
            genres.forEach {
                Genre(genre = it, onDeleteButtonClick = onDeleteButtonClick)
            }
        }
    }
}

@Composable
fun Genre(
    modifier: Modifier = Modifier,
    genre: String,
    onDeleteButtonClick: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = genre,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onPrimary
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "Remove",
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .size(15.dp)
                .clickable {
                    onDeleteButtonClick.invoke(genre)
                }
        )
    }
}
