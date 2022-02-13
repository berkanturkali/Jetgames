package com.example.jetgames.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.jetgames.core.domain.model.games.GameModel
import com.example.jetgames.core.domain.model.games.lowerBound
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

    val homeState: StateFlow<HomeState> get() = _homeState

    init {
        viewModelScope.launch {
            combine(_refreshing, _isGridMode) { refreshing, isGridMode ->
                HomeState(isRefreshing = refreshing, isGalleryMode = isGridMode)
            }
                .catch { throwable ->
                }
                .collectLatest {
                    _homeState.value = it
                }
        }
    }

    val games = useCase.execute()
        .map { pagingData ->
            pagingData.map {
                GameModel.GameItem(it)
            }
        }
        .map {
            it.insertSeparators { before, after ->
                if (after == null) {
                    return@insertSeparators null
                }
                if (before == null) {
                    return@insertSeparators GameModel.SeparatorItem("95-99 Metascore")
                }
                if (before.game.metaCritic != null) {
                    val lower = before.lowerBound()
                    if (after.game.metaCritic != null) {
                        if (after.game.metaCritic!! < lower) {
                            GameModel.SeparatorItem("${lower - 5}-${lower -1} Metascore")
                        } else {
                            null
                        }
                    } else {
                        null
                    }
                } else {
                    null
                }
            }
        }.cachedIn(viewModelScope)


    fun setRefresh(isRefreshing: Boolean) {
        _refreshing.value = isRefreshing
    }

    fun setGalleryMode(isGalleryMode:Boolean){
        _isGridMode.value = isGalleryMode
    }
}