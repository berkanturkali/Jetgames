package com.example.jetgames.filter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetgames.core.domain.model.games.Genre
import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.core.domain.model.preferences.HomePreferences
import com.example.jetgames.core.domain.model.preferences.MetacriticPreference
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
        MutableStateFlow<MutableList<Platform>?>(
            null)

    private val _selectedGenres = MutableStateFlow<List<Genre>?>(null)

    private val _selectedMetacritic = MutableStateFlow(MetacriticPreference())

    private val _filterState = MutableStateFlow(FilterState())

    private val _preferencesApplied = MutableLiveData<Boolean>()

    val preferencesApplied: LiveData<Boolean> get() = _preferencesApplied

    val filterState: StateFlow<FilterState> get() = _filterState

    private var currentPrefs = HomePreferences.HomeFilterPreferences()


    init {
        getPreferences()
        viewModelScope.launch {
            combine(
                _selectedPlatforms,
                _selectedGenres,
                _selectedMetacritic) { platforms, genres, metacritic ->
                FilterState(
                    selectedPlatforms = platforms,
                    selectedGenres = genres,
                    selectedMetacritics = metacritic,
                    isApplyButtonVisible = if (currentPrefs.platforms != null) {
                        // TODO: temp logic
                        currentPrefs.platforms?.sortedBy(Platform::id) != platforms?.sortedBy(Platform::id)
                    } else {
                        !platforms.isNullOrEmpty()
                    }
                )
            }
                .catch { throwable ->

                }
                .collect {
                    _filterState.value = it
                }
        }
    }

    fun setPlatforms(platforms: List<Platform>?) {
        _selectedPlatforms.value = platforms as MutableList<Platform>?
    }

    fun setGenres(genres: List<Genre>?) {
        _selectedGenres.value = genres
    }

    fun setMetacritics(metacritic: MetacriticPreference) {
        _selectedMetacritic.value = metacritic
    }

    fun removePlatform(platform: Platform) {
        val newList = _selectedPlatforms.value!! - platform
        setPlatforms(newList)
    }

    fun applyPreferences() {
        val preferences = HomePreferences.HomeFilterPreferences(
            platforms = _selectedPlatforms.value
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
            }
        }
    }
}