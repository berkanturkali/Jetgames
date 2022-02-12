package com.example.jetgames.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme

@Composable
fun HomeToolbar(
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(modifier = modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.primary),
        elevation = 12.dp) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .wrapContentHeight()) {
            TextField(modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(dimensionResource(id = R.dimen.dimen_8)),
                shape = androidx.compose.foundation.shape.CircleShape,
                value = "",
                onValueChange = { /* will be implemented*/ },
                label = {
                    Text(text = stringResource(id = R.string.search),
                        modifier = Modifier.align(CenterVertically),
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                leadingIcon = {
                    Icon(Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.search),
                        tint = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f))
                },
                textStyle = TextStyle(color = MaterialTheme.colors.onPrimary),
                colors =
                TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.primaryVariant.copy(alpha = 0.1f),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent)
            )

            Row(modifier = Modifier
                .align(Alignment.CenterVertically)) {

                Icon(modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.dimen_8))
                    .clickable { /* will be implemented */ },
                    imageVector = Icons.Filled.GridView,
                    contentDescription = "Gallery/List Mode",
                    tint = MaterialTheme.colors.onPrimary.copy(alpha=0.7f))
            }
        }
    }
}

@Preview()
@Composable
fun HomeToolbarPrev() {
    JetgamesTheme {
        HomeToolbar()
    }
}