package com.example.jetgames.filter.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetgames.common.DefaultScreenUI
import com.example.jetgames.common.components.ErrorItem
import com.example.jetgames.common.components.LoadingItem
import com.example.jetgames.core.domain.util.Resource
import com.example.jetgames.filter.components.ApplyFilterFab
import com.example.jetgames.filter.components.BottomSheetDialogToolbar
import com.example.jetgames.filter.components.PlatformItem
import com.example.jetgames.filter.viewmodel.PlatformsScreenViewModel
import timber.log.Timber

@Composable
fun PlatformsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PlatformsScreenViewModel = hiltViewModel(),
) {
    val platforms = viewModel.platforms.observeAsState()

    DefaultScreenUI(toolbar = {
        BottomSheetDialogToolbar(title = "Platforms")
    },
        floatingActionButton = {
            AnimatedVisibility(visible = true,
                enter = expandVertically(),
                exit = shrinkVertically()) {
                ApplyFilterFab()
            }
        }
    ) {

        when (platforms.value) {
            is Resource.Loading -> {
                LoadingItem()
            }
            is Resource.Error -> {
                ErrorItem(message = platforms.value!!.error!!,
                    onRetryClick = viewModel::setRefresh)
            }
            is Resource.Success -> {
                if (platforms.value!!.data!!.isNotEmpty()) {
                    LazyColumn(modifier = modifier.fillMaxSize()) {
                        items(count = platforms.value!!.data!!.size) {
                            //platform item
                            PlatformItem(platform = platforms.value!!.data!![it])
                        }
                    }
                } else {
                    //empty view
                }
            }
        }
    }
}