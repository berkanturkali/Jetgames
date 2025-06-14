package com.example.jetgames.filter.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.common.R
import com.example.jetgames.common.components.ErrorItem
import com.example.jetgames.common.components.LoadingItem
import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.core.domain.util.Resource
import com.example.jetgames.filter.components.BottomSheetDialogToolbar
import com.example.jetgames.filter.components.PlatformItem
import com.example.jetgames.filter.viewmodel.PlatformsScreenViewModel

@Composable
fun PlatformsScreen(
    modifier: Modifier = Modifier,
    items: List<Platform>,
    viewModel: PlatformsScreenViewModel = hiltViewModel(),
    onApplyButtonClick: (List<Platform>) -> Unit,
) {
    val platforms = viewModel.platforms.observeAsState()

    val listState = rememberLazyListState()

    val selectedPlatforms by rememberSaveable {
        mutableStateOf(items.toMutableList())
    }

    var isApplyButtonActive by rememberSaveable {
        mutableStateOf(false)
    }
    DefaultScreenUI(
        toolbar = {
            BottomSheetDialogToolbar(title = "Platforms")
        },
    ) {

        when (platforms.value) {
            is Resource.Loading<*> -> {
                LoadingItem(modifier = Modifier.fillMaxSize())
            }
            is Resource.Error<*> -> {
                ErrorItem(
                    modifier = Modifier
                        .fillMaxSize(),
                    message = platforms.value!!.error!!,
                    onRetryClick = viewModel::setRefresh
                )
            }
            is Resource.Success<*> -> {
                if (platforms.value!!.data!!.isNotEmpty()) {
                    val sortedList =
                        viewModel.sortPlatforms(platforms.value!!.data!!)
                    LazyColumn(state = listState, modifier = modifier) {
                        items(count = platforms.value!!.data!!.size) {
                            // platform item
                            PlatformItem(
                                flag = selectedPlatforms.contains(sortedList[it]),
                                platform = sortedList[it],
                            ) { platform, selected ->
                                if (!selected) {
                                    selectedPlatforms.remove(platform)
                                } else {
                                    selectedPlatforms.add(platform)
                                }
                                isApplyButtonActive = viewModel.isApplyButtonActive(items, selectedPlatforms)
                            }
                        }
                        item {
                            Button(
                                onClick = { onApplyButtonClick(selectedPlatforms) },
                                enabled = isApplyButtonActive,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = MaterialTheme.colors.secondary,
                                    contentColorFor(backgroundColor = MaterialTheme.colors.onSecondary)
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(dimensionResource(id = R.dimen.dimen_8))
                            ) {
                                Text(text = "Apply")
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
