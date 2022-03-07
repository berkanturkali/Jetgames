package com.example.jetgames.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.jetgames.core.domain.model.games.GameModel
import com.example.jetgames.core.domain.model.games.lowerBound
import com.example.jetgames.core.domain.model.games.preferences.HomePreferences
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

    private val _homeViewPreferences = MutableStateFlow(HomePreferences.HomeViewPreferences())

    private val _homeFilterPreferences = MutableStateFlow(HomePreferences.HomeFilterPreferences())

    private val _homeState = MutableStateFlow(HomeState())

    private val _query = MutableStateFlow<String?>(null)

    val homeState: StateFlow<HomeState> get() = _homeState

    init {
        viewModelScope.launch {
            combine(_refreshing,
                _homeViewPreferences,
                _homeFilterPreferences) { refreshing, viewPreferences, filterPreferences ->
                HomeState(isRefreshing = refreshing,
                    homeViewPreferences = viewPreferences,
                    homeFilterPreferences = filterPreferences)
            }
                .catch { throwable ->
                }
                .collectLatest {
                    _homeState.value = it
                }
        }
        _query
            .debounce(300)
            .map {
                it?.trim()
            }
            .onEach {
                _homeFilterPreferences.value = _homeFilterPreferences.value.copy(query = it)
            }.launchIn(viewModelScope)
    }

    val games = _homeFilterPreferences.flatMapLatest {
        useCase.execute(it)
            .map { pagingData ->
                pagingData.map {
                    GameModel.GameItem(it)
                }
            }
    }
        .map {
            it.insertSeparators { before, after ->
                if (after == null) {
                    return@insertSeparators null
                }
                if (before == null) {
                    if (after.game.metaCritic != null) {
                        val lower = after.lowerBound()
                        return@insertSeparators GameModel.SeparatorItem("${lower}-${after.game.metaCritic} Metascore")
                    }
                }
                if (before?.game?.metaCritic != null) {
                    val lower = before.lowerBound()
                    if (after.game.metaCritic != null) {
                        if (after.game.metaCritic!! < lower) {
                            GameModel.SeparatorItem("${lower - 5}-${lower - 1} Metascore")
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

    fun setGalleryMode(isGalleryMode: Boolean) {
        _homeViewPreferences.value =
            _homeViewPreferences.value.copy(isGalleryMode = isGalleryMode)
    }

    fun setQuery(query: String?) {
        _query.value = query
    }
}