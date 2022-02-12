package com.example.jetgames.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.common.ui.theme.XXLightGray

@Composable
fun SeparatorItem(modifier: Modifier = Modifier, separator: String) {

    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(MaterialTheme.colors.primary)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.dimen_16),
                    vertical = dimensionResource(
                        id = R.dimen.dimen_16)),
            text = separator,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h4)

        Divider(color = XXLightGray.copy(alpha = 0.4f), thickness = 0.5.dp)
    }
}

@Preview
@Composable
fun SeparatorPrev() {
    JetgamesTheme() {
        SeparatorItem(separator = "90+ Metacritic")
    }
}