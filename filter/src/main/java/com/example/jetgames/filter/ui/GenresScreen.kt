package com.example.jetgames.filter.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.common.components.ErrorItem
import com.example.jetgames.common.components.LoadingItem
import com.example.jetgames.core.domain.util.Resource
import com.example.jetgames.filter.components.FilterToolbar
import com.example.jetgames.filter.components.GenreItem
import com.example.jetgames.filter.viewmodel.GenresScreenViewModel

@Composable
fun GenresScreen(
    modifier: Modifier = Modifier,
    items: List<String>,
    viewModel: GenresScreenViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    onApplyButtonClick: (List<String>) -> Unit,
) {

    val genres = viewModel.genres.observeAsState()

    val listState = rememberLazyListState()

    var isApplyButtonActive by rememberSaveable {
        mutableStateOf(false)
    }

    val selectedGenres by rememberSaveable {
        mutableStateOf(items.toMutableList())
    }

    DefaultScreenUI(
        toolbar = {
            FilterToolbar(
                title = "Genres",
                navigateUp = navigateUp,
                enableApplyButton = isApplyButtonActive,
                onApplyButtonClick = {
                    onApplyButtonClick(selectedGenres)
                })
        },
    ) {

        when (genres.value) {
            is Resource.Loading<*> -> {
                LoadingItem(modifier = Modifier.fillMaxSize())
            }

            is Resource.Error<*> -> {
                ErrorItem(
                    modifier = Modifier
                        .fillMaxSize(),
                    message = genres.value!!.error!!,
                    onRetryClick = viewModel::setRefresh
                )
            }

            is Resource.Success<*> -> {
                if (genres.value!!.data!!.isNotEmpty()) {
                    val sortedList =
                        viewModel.sortGenres(genres.value!!.data!!)
                    LazyColumn(state = listState, modifier = modifier) {
                        items(count = genres.value!!.data!!.size) {
                            // platform item
                            GenreItem(
                                isChecked = selectedGenres.contains(sortedList[it].name),
                                genre = sortedList[it],
                            ) { genre, checked ->
                                if (checked) {
                                    selectedGenres.add(genre)
                                } else {
                                    selectedGenres.remove(genre)
                                }
                                isApplyButtonActive =
                                    viewModel.isApplyButtonActive(items, selectedGenres)
                            }
                        }
                    }
                } else {
                    // empty view
                }
            }

            null -> Unit
        }
    }
}
