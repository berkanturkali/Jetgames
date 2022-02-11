package com.example.jetgames.home.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
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

@Composable
fun HomeToolbar(
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(modifier = modifier
        .fillMaxWidth(),
        elevation = 12.dp) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {
            TextField(modifier = Modifier
                .fillMaxWidth(.8f)
                .padding(dimensionResource(id = R.dimen.dimen_8)),
                shape = androidx.compose.foundation.shape.CircleShape,
                value = "",
                onValueChange = { /* will be implemented*/ },
                label = {
                    Text(text = stringResource(id = R.string.search),
                        color = Color.LightGray)
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
                        tint = Color.LightGray)
                },
                textStyle = TextStyle(color = Color.White),
                colors =
                TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeToolbarPrev() {
    HomeToolbar()
}