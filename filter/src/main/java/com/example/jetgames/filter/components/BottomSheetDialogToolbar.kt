package com.example.jetgames.filter.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.common.ui.theme.XXLightGray

@Composable
fun BottomSheetDialogToolbar(
    modifier: Modifier = Modifier,
    title: String,
    onCloseButtonClick: () -> Unit,
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        Icon(painter = painterResource(id = R.drawable.ic_rule),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = dimensionResource(id = R.dimen.dimen_8)),
            tint = Color.Gray.copy(alpha = 0.5f))
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = dimensionResource(
                id = R.dimen.dimen_16),
                vertical = dimensionResource(id = R.dimen.dimen_16)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.h6,
            )
            Icon(painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier.clickable { onCloseButtonClick() }
            )
        }
        Divider(thickness = 0.5.dp, color = XXLightGray.copy(alpha = 0.5f))
    }
}

@Preview
@Composable
fun BottomSheetDialogToolbarPrev() {
    JetgamesTheme {
        BottomSheetDialogToolbar(title = "Metacritic") {

        }
    }
}

