package com.example.jetgames.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetgames.core.domain.executor.abstraction.PostExecutionThread
import com.example.jetgames.core.domain.model.favorites.Favorite
import com.example.jetgames.core.domain.repo.FavoritesRepo
import com.example.jetgames.favorites.state.FavoritesScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repo: FavoritesRepo,
    private val postExecutionThread: PostExecutionThread,
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<Favorite>>(emptyList())

    private val _isFavoriteItemRemoved = MutableStateFlow(false)

    private val _favoritesScreenState = MutableStateFlow(FavoritesScreenState())

    val favoritesScreenState: StateFlow<FavoritesScreenState> get() = _favoritesScreenState

    init {
        getFavorites()
        viewModelScope.launch {
            combine(
                _favorites,
                _isFavoriteItemRemoved
            ) { favorites, isRemoved ->
                FavoritesScreenState(
                    favorites = favorites,
                    showEmptyView = favorites.isEmpty()
                )
            }
                .collect {
                    _favoritesScreenState.value = it
                }
        }
    }

    private fun getFavorites() {
        repo.favorites()
            .onEach {
                _favorites.value = it
            }
            .launchIn(viewModelScope)
    }

    fun removeFromFavorites(favorite: Favorite) {
        viewModelScope.launch(postExecutionThread.io) {
            repo.delete(favorite)
        }
    }
}
