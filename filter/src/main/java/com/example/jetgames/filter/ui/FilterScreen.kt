package com.example.jetgames.filter.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.filter.components.*
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
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                title = { ToolbarTitle() },
                navigationIcon = {
                    NavigationIcon(onNavigationClick = navigateUp)
                }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                enter = scaleIn(),
                exit = scaleOut(),
                visible = screenState.value.isApplyButtonVisible
            ) {
                ApplyFilterFab() {
                    viewModel.applyPreferences()
                }
            }
        }
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
