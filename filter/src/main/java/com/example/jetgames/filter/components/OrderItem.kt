package com.example.jetgames.filter.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.R

@Composable
fun OrderItem(
    modifier: Modifier = Modifier,
    order: String,
    selected: Boolean,
    onItemSelected: (String) -> Unit,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.dimen_8)),
            text = order,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary
        )

        RadioButton(
            selected = selected,
            onClick = {
                onItemSelected(order)
            }
        )
    }
}
