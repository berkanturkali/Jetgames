package com.example.jetgames.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.jetgames.core.domain.usecase.games.GamesUseCase
import com.example.jetgames.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GamesUseCase,
) : ViewModel() {

    private val _refreshing = MutableStateFlow(false)

    private val _isGridMode = MutableStateFlow(false)

    private val _homeState = MutableStateFlow<HomeState>(HomeState())

    val homeState:StateFlow<HomeState> get() = _homeState

    init {
        viewModelScope.launch {
            combine(_refreshing, _isGridMode) { refreshing, isGridMode ->
                HomeState(isRefreshing = refreshing, isGalleryMode = isGridMode)
            }
                .catch { throwable->

                }
                .collectLatest {
                    _homeState.value = it
                }
        }
    }

    val games = useCase.execute().cachedIn(viewModelScope)


    fun setRefresh(isRefreshing:Boolean){
        _refreshing.value = isRefreshing
    }
}