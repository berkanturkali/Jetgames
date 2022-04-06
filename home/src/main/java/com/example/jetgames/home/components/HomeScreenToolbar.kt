package com.example.jetgames.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetgames.common.JetgamesSurface
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.home.viewmodel.HomeViewModel

@Composable
fun HomeToolbar(
    modifier: Modifier = Modifier,
    galleryListToggleClick: () -> Unit,
    filterClick: () -> Unit,
    viewModel: HomeViewModel? = null,
    prefCount: Int = 0,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val focusRequester = remember {
        FocusRequester()
    }

    val focusManager = LocalFocusManager.current

    var query by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    JetgamesSurface(
        modifier = modifier
            .fillMaxWidth(),
        elevation = dimensionResource(id = R.dimen.dimen_8)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(dimensionResource(id = R.dimen.dimen_8))
                    .focusRequester(focusRequester),
                shape = androidx.compose.foundation.shape.CircleShape,
                value = query ?: "",
                onValueChange = {
                    query = it.ifEmpty { null }
                    viewModel?.setQuery(it.ifEmpty { null })
                    if (query.isNullOrEmpty()) focusManager.clearFocus()
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.search),
                        modifier = Modifier.align(CenterVertically),
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.search),
                        tint = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f)
                    )
                },
                textStyle = TextStyle(color = MaterialTheme.colors.onPrimary),
                colors =
                TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.primaryVariant.copy(alpha = 0.1f),
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.onPrimary,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    AnimatedVisibility(
                        enter = scaleIn(),
                        exit = scaleOut(),
                        visible = !query.isNullOrEmpty() || !query.isNullOrBlank()
                    ) {
                        Icon(
                            tint = MaterialTheme.colors.onPrimary,
                            painter = painterResource(id = R.drawable.ic_clear),
                            contentDescription = stringResource(
                                id = R.string.clear_query
                            ),
                            modifier = Modifier
                                .padding(horizontal = dimensionResource(id = R.dimen.dimen_8))
                                .clickable {
                                    query = null
                                    viewModel?.setQuery(null)
                                    focusManager.clearFocus()
                                }
                        )
                    }
                },
                singleLine = true
            )

            Row(
                modifier = Modifier
                    .align(CenterVertically)
            ) {
                BadgedBox(badge = {
                    Column {
                        AnimatedVisibility(
                            visible = prefCount != 0,
                            enter = scaleIn(),
                            exit = scaleOut()
                        ) {
                            Badge(
                                backgroundColor = MaterialTheme.colors.secondary,
                                contentColor = MaterialTheme.colors.onSecondary
                            ) { Text(text = prefCount.toString()) }
                        }
                    }
                }) {
                    Icon(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.dimen_8))
                            .clickable { filterClick.invoke() },
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "Filter",
                        tint = MaterialTheme.colors.onPrimary.copy(alpha = 0.7f)
                    )
                }

                Icon(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.dimen_8))
                        .clickable { galleryListToggleClick.invoke() },
                    imageVector = Icons.Filled.GridView,
                    contentDescription = "Gallery/List Mode",
                    tint = MaterialTheme.colors.onPrimary.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Preview()
@Composable
fun HomeToolbarPrev() {
    JetgamesTheme {
        HomeToolbar(
            filterClick = {},
            galleryListToggleClick = {},
            prefCount = 5
        )
    }
}
