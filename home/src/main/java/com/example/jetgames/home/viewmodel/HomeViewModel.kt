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
import com.example.jetgames.home.model.SeparatorGenerator
import com.example.jetgames.home.state.HomeState
import com.example.jetgames.home.util.toSeparatorGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import timber.log.Timber
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
        Timber.d("HomeViewModel initialized")
        viewModelScope.launch {
            combine(
                _refreshing,
                _homeViewPreferences,
                _homeFilterPreferences
            ) { refreshing, viewPreferences, filterPreferences ->
                HomeState(
                    isRefreshing = refreshing,
                    filterCount = calculateBadge(
                        filterPreferences.platforms,
                        filterPreferences.genres,
                        metacriticPreference = filterPreferences.metacriticPreference
                    ),
                    homeViewPreferences = viewPreferences,
                    homeFilterPreferences = filterPreferences
                )
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
        useCase(it.second.copy(query = it.first))
            .map { pagingData ->
                pagingData.map {
                    GameModel.GameItem(it)
                }
            }
            .map {
                it.insertSeparators { before, after ->
                    val generator = _homeState.value.homeFilterPreferences.order.order.toSeparatorGenerator()
                    generateSeparator(before = before, after = after, generator = generator)
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

    fun getIcon(game: Game): String? {
        val rating = game.ratings?.maxByOrNull { it?.percent!! }
        return rating?.icon
    }

    private fun generateSeparator(
        before: GameModel.GameItem?,
        after: GameModel.GameItem?,
        generator: SeparatorGenerator
    ): GameModel.SeparatorItem? {
        return when {
            after == null -> null
            before == null -> generator.generateInitial(after)
            else -> generator.generateBetween(before, after)
        }
    }
}