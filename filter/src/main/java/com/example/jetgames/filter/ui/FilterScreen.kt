package com.example.jetgames.filter.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.filter.components.*
import com.example.jetgames.filter.viewmodel.FilterScreenViewModel

@Composable
fun FilterScreen(
    modifier: Modifier = Modifier,
    onFilterItemClick: (String) -> Unit,
    viewModel: FilterScreenViewModel,
    navigateUp: () -> Unit,
) {

    val screenState = viewModel.filterState.collectAsState()

    val preferencesApplied = viewModel.preferencesApplied.observeAsState()

    preferencesApplied.value?.let {
        if (it) {
            navigateUp.invoke()
        } else {
            // TODO: show retry snack
        }
    }

    DefaultScreenUI(
        toolbar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                title = { ToolbarTitle() },
                navigationIcon = {
                    NavigationIcon(onNavigationClick = navigateUp)
                })
        },
        floatingActionButton = {
            AnimatedVisibility(
                enter = scaleIn(),
                exit = scaleOut(),
                visible = screenState.value.isApplyButtonVisible) {
                ApplyFilterFab() {
                    viewModel.applyPreferences()
                }
            }
        }
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            Divider(thickness = 0.5.dp)
            //platform filter
            Platforms(
                platforms = screenState.value.selectedPlatforms ?: emptyList(),
                onFilterItemClick = onFilterItemClick) { platform ->
                viewModel.removePlatform(platform)
            }
            Divider(thickness = 0.5.dp)

            //genres filter
            Genres(onGenresItemClick = onFilterItemClick,
                genres = screenState.value.selectedGenres ?: emptyList(),
                onDeleteButtonClick = viewModel::removeGenre)
            Divider(thickness = 0.5.dp)

            //metacritic filter
            Metacritic()
            Divider(thickness = 0.5.dp)
        }
    }
}