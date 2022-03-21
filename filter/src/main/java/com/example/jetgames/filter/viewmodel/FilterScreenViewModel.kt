package com.example.jetgames.filter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.core.domain.model.preferences.*
import com.example.jetgames.core.domain.repo.PreferencesRepo
import com.example.jetgames.filter.state.FilterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FilterScreenViewModel @Inject constructor(
    private val preferencesRepo: PreferencesRepo,
) : ViewModel() {

    private val _selectedPlatforms =
        MutableStateFlow<List<Platform>>(
            emptyList())

    private val _selectedGenres = MutableStateFlow<List<String>>(emptyList())

    private val _selectedMetacritic = MutableStateFlow(MetacriticPreference())

    private val _selectedOrder = MutableStateFlow(OrderPreference())

    private val _filterState = MutableStateFlow(FilterState())

    private val _preferencesApplied = MutableLiveData<Boolean>()

    val preferencesApplied: LiveData<Boolean> get() = _preferencesApplied

    val filterState: StateFlow<FilterState> get() = _filterState

    private var currentPrefs = HomePreferences.HomeFilterPreferences()

    private val _min = MutableStateFlow(0f)

    val min: StateFlow<Float> get() = _min

    private val _max = MutableStateFlow(100f)

    val max: StateFlow<Float> get() = _max

    init {
        getPreferences()
        viewModelScope.launch {
            combine(
                _selectedPlatforms,
                _selectedGenres,
                _selectedMetacritic,
                _selectedOrder) { platforms, genres, metacritic, order ->
                FilterState(
                    selectedOrder = order,
                    selectedPlatforms = platforms,
                    selectedGenres = genres,
                    selectedMetacritics = metacritic,
                    isApplyButtonVisible = setApplyButtonVisibility(
                        platforms = platforms,
                        genres = genres,
                        metacritic = metacritic,
                        order = order)
                )
            }
                .catch { throwable ->

                }
                .collect {
                    _filterState.value = it
                }
        }
    }

    fun setPlatforms(platforms: List<Platform>) {
        _selectedPlatforms.value = platforms
    }

    fun setGenres(genres: List<String>) {
        _selectedGenres.value = genres

    }

    fun setMetacritics(metacritic: MetacriticPreference) {
        _selectedMetacritic.value = metacritic
    }

    fun removePlatform(platform: Platform) {
        val newList = _selectedPlatforms.value - platform
        setPlatforms(newList)
    }

    fun removeGenre(genre: String) {
        val newList = _selectedGenres.value - genre
        setGenres(newList)
    }

    private fun setApplyButtonVisibility(
        platforms: List<Platform>,
        genres: List<String>,
        metacritic: MetacriticPreference,
        order: OrderPreference,
    ): Boolean {
        return !(platforms.sortedBy(Platform::id) == currentPrefs.platforms.sortedBy(Platform::id) &&
                genres.sorted() == currentPrefs.genres.sorted() &&
                metacritic == currentPrefs.metacriticPreference &&
                order == currentPrefs.order
                )
    }

    fun applyPreferences() {
        val preferences = HomePreferences.HomeFilterPreferences(
            platforms = _selectedPlatforms.value,
            genres = _selectedGenres.value,
            metacriticPreference = _selectedMetacritic.value,
            order = _selectedOrder.value
        )
        viewModelScope.launch(Dispatchers.Main) {
            _preferencesApplied.value =
                withContext(Dispatchers.IO) { preferencesRepo.insertHomePreferences(preferences) } >= 0
        }
    }

    private fun getPreferences() {
        viewModelScope.launch {
            preferencesRepo.preferences().collect {
                currentPrefs = it
                setPlatforms(it.platforms)
                setGenres(it.genres)
                setMetacritics(it.metacriticPreference)
                _min.value = it.metacriticPreference.min.toFloat()
                _max.value = it.metacriticPreference.max.toFloat()
                _selectedOrder.value = it.order
            }
        }
    }

    private fun isMinValid(min: Int, max: Int) = min < max

    fun setMin(min: Float) {
        if (!isMinValid(min.toInt(), max.value.toInt())) {
            _min.value = max.value
        } else {
            _min.value = min
        }
    }

    fun setMax(max: Float) {
        _max.value = max
    }

    fun onValueChangeFinishedForMax() {
        if (_max.value.toInt() < min.value.toInt()) {
            setMin(_max.value)
        }
        _selectedMetacritic.value = _selectedMetacritic.value.copy(max = _max.value.toInt())
    }

    fun onValueChangeFinishedForMin() {
        _selectedMetacritic.value = _selectedMetacritic.value.copy(min = min.value.toInt())
    }

    fun setSelectedOrder(order: String) {
        val enumOrder = when (order.lowercase()) {
            Order.METACRITIC.value -> Order.METACRITIC
            Order.RATING.value -> Order.RATING
            else -> throw Exception("invalid order")
        }
        _selectedOrder.value = _selectedOrder.value.copy(order = enumOrder)
    }
}