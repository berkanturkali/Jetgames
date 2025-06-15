package com.example.jetgames.filter.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.filter.components.ApplyFilterFab
import com.example.jetgames.filter.components.FilterToolbar
import com.example.jetgames.filter.components.Genres
import com.example.jetgames.filter.components.Metacritic
import com.example.jetgames.filter.components.NavigationIcon
import com.example.jetgames.filter.components.Order
import com.example.jetgames.filter.components.Platforms
import com.example.jetgames.filter.components.ToolbarTitle
import com.example.jetgames.filter.viewmodel.FilterScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun FilterScreen(
    modifier: Modifier = Modifier,
    onFilterItemClick: (String) -> Unit,
    viewModel: FilterScreenViewModel,
    navigateUp: () -> Unit,
) {

    val screenState = viewModel.filterState.collectAsState()

    val preferencesApplied = viewModel.preferencesApplied.observeAsState()

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    preferencesApplied.value?.let {
        if (it) {
            navigateUp.invoke()
        } else {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar("Something went wrong.")
            }
        }
    }

    DefaultScreenUI(
        state = scaffoldState,
        toolbar = {
            FilterToolbar(
                title = "Filter",
                enableApplyButton = screenState.value.isApplyButtonVisible,
                onApplyButtonClick = {
                    viewModel.applyPreferences()
                },
            ) {
                navigateUp()
            }
        },
    ) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            item {
                Divider(thickness = 0.5.dp)
                // platform filter
                Platforms(
                    platforms = screenState.value.selectedPlatforms ?: emptyList(),
                    onFilterItemClick = onFilterItemClick
                ) { platform ->
                    viewModel.removePlatform(platform)
                }
            }
            item {
                Divider(thickness = 0.5.dp)

                // genres filter
                Genres(
                    onGenresItemClick = onFilterItemClick,
                    genres = screenState.value.selectedGenres ?: emptyList(),
                    onDeleteButtonClick = viewModel::removeGenre
                )
            }
            item {
                Divider(thickness = 0.5.dp)

                // order by
                Order(
                    onOrderItemClick = onFilterItemClick,
                    orderPref = screenState.value.selectedOrder
                )

                Divider(thickness = 0.5.dp)
            }

            item {

                // metacritic filter
                Metacritic(viewModel = viewModel)
                Divider(thickness = 0.5.dp)
            }
        }
    }
}
