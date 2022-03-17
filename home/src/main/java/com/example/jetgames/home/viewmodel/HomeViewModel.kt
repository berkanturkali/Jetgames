package com.example.jetgames.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.jetgames.core.domain.model.games.GameModel
import com.example.jetgames.core.domain.model.games.lowerBound
import com.example.jetgames.core.domain.model.preferences.HomePreferences
import com.example.jetgames.core.domain.model.preferences.Order
import com.example.jetgames.core.domain.model.preferences.OrderPreference
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

    private val _selectedOrder = MutableStateFlow(OrderPreference())

    init {
        viewModelScope.launch {
            combine(
                _selectedOrder,
                _refreshing,
                _homeViewPreferences,
                _homeFilterPreferences) { selectedOrder, refreshing, viewPreferences, filterPreferences ->
                HomeState(
                    selectedOrder = selectedOrder,
                    isRefreshing = refreshing,
                    filterCount = calculateBadge(filterPreferences.platforms),
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

    // TODO: separator logic bug will be fixed
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
                        if (after.game.metaCritic != null) {
                            val lower = after.lowerBound()
                            return@insertSeparators GameModel.SeparatorItem("${lower}-${after.game.metaCritic} Metascore")
                        }
                    }
                    if (before?.game?.metaCritic != null) {
                        val lower = before.lowerBound()
                        /* if before's metacritic is for example 67
                         * and after's is 58
                         * since the lower of the 67 is 65
                         * and after's metacri is lower than the lower
                         * it returns separator with "60-64 Metascore" before the after item
                         */
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

    private fun calculateBadge(vararg lists: List<Any>?): Int {
        return lists.filter { !it.isNullOrEmpty() }.size
    }

    fun orderOptions(): List<OrderPreference> {
        return listOf(
            OrderPreference(
                order = Order.NAME
            ),
            OrderPreference(
                order = Order.RELEASED
            ),
            OrderPreference(
                order = Order.RATING
            ),
            OrderPreference(
                order = Order.METACRITIC
            ),
        )
    }

    fun setOrder(orderPreference: OrderPreference) {
        _selectedOrder.value = orderPreference
    }

}