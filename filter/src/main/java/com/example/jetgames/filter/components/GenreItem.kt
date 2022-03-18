package com.example.jetgames.filter.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.R
import com.example.jetgames.core.domain.model.genres.Genre

@Composable
fun GenreItem(
    modifier: Modifier = Modifier,
    genre: Genre,
) {

    Row(modifier = modifier
        .fillMaxWidth()
        .height(40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.dimen_8)),
            text = genre.name,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary)
        Checkbox(
            checked = true,
            onCheckedChange = {

            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.secondary,
                checkmarkColor = MaterialTheme.colors.onPrimary
            ),
        )

    }

}