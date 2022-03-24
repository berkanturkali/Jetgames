package com.example.jetgames.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.jetgames.core.domain.model.games.Game
import com.example.jetgames.core.domain.model.games.GameModel
import com.example.jetgames.core.domain.model.games.lowerBound
import com.example.jetgames.core.domain.model.games.upper
import com.example.jetgames.core.domain.model.preferences.HomePreferences
import com.example.jetgames.core.domain.model.preferences.MetacriticPreference
import com.example.jetgames.core.domain.model.preferences.Order
import com.example.jetgames.core.domain.repo.PreferencesRepo
import com.example.jetgames.core.domain.usecase.games.GamesUseCase
import com.example.jetgames.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GamesUseCase,
    private val preferencesRepo: PreferencesRepo,
) : ViewModel() {

    private val _refreshing = MutableStateFlow(false)

    private val _homeViewPreferences = MutableStateFlow(HomePreferences.HomeViewPreferences())

    private val _homeFilterPreferences = preferencesRepo.preferences()

    private val _homeState = MutableStateFlow(HomeState())

    private val _query = MutableStateFlow<String?>(null)

    private val query: Flow<String?> get() = _query.debounce(300).mapLatest { it?.trim() }

    val homeState: StateFlow<HomeState> get() = _homeState

    init {
        viewModelScope.launch {
            combine(
                _refreshing,
                _homeViewPreferences,
                _homeFilterPreferences) { refreshing, viewPreferences, filterPreferences ->
                HomeState(
                    isRefreshing = refreshing,
                    filterCount = calculateBadge(filterPreferences.platforms,
                        filterPreferences.genres,
                        metacriticPreference = filterPreferences.metacriticPreference),
                    homeViewPreferences = viewPreferences,
                    homeFilterPreferences = filterPreferences)
            }
                .catch { throwable ->
                }
                .collect {
                    _homeState.value = it
                }
        }
    }

    val games = combine(
        query,
        _homeFilterPreferences,
        ::Pair
    ).flatMapLatest {
        useCase.execute(it.second.copy(query = it.first))
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
                        when (_homeState.value.homeFilterPreferences.order.order) {
                            Order.METACRITIC -> {
                                if (after.game.metaCritic != null) {
                                    val lower = after.lowerBound()
                                    if (lower == after.game.metaCritic) {
                                        return@insertSeparators GameModel.SeparatorItem("$lower Metascore")
                                    } else {
                                        return@insertSeparators GameModel.SeparatorItem("${lower}-${after.game.metaCritic} Metascore")
                                    }
                                }
                            }
                            Order.RATING -> {
                                if (after.game.rating != null) {
                                    val plus = if (after.game.rating!!.toInt() == 5) "" else "+"
                                    val separator = "${after.game.rating!!.toInt()}$plus Stars"
                                    return@insertSeparators GameModel.SeparatorItem(separator)
                                }
                            }
                        }
                    }
                    when (_homeState.value.homeFilterPreferences.order.order) {
                        Order.RATING -> {
                            if (before?.game?.rating != null) {
                                if (after.game.rating != null) {
                                    if (after.game.rating!!.toInt() < before.game.rating!!.toInt()) {
                                        GameModel.SeparatorItem("${after.game.rating!!.toInt()} + Stars")
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
                        Order.METACRITIC -> {
                            if (before?.game?.metaCritic != null) {
                                val lower = before.lowerBound()
                                if (after.game.metaCritic != null) {
                                    if (after.game.metaCritic!! < lower) {
                                        if (after.game.metaCritic!! + 1 != lower) {
                                            val upper = after.upper()
                                            GameModel.SeparatorItem("${upper - 5}-${upper - 1} Metascore")
                                        } else {
                                            GameModel.SeparatorItem("${lower - 5}-${lower - 1} Metascore")
                                        }
                                    } else {
                                        null
                                    }
                                } else {
                                    GameModel.SeparatorItem("No Metascore")
                                }
                            } else {
                                null
                            }
                        }
                    }
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

    private fun calculateBadge(
        vararg lists: List<Any>?,
        metacriticPreference: MetacriticPreference,
    ): Int {
        val listsFilterCount = lists.filter { !it.isNullOrEmpty() }.size
        val metacriFilter =
            if (metacriticPreference.min != 0) 1 else 0
        return listsFilterCount + metacriFilter
    }

    fun getIcon(game:Game):String?{
        val rating = game.ratings?.maxByOrNull { it?.percent!! }
        return rating?.icon
    }
}