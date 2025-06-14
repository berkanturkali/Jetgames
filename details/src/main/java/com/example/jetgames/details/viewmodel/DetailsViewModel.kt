package com.example.jetgames.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.jetgames.core.domain.executor.abstraction.PostExecutionThread
import com.example.jetgames.core.domain.model.detail.GameDetails
import com.example.jetgames.core.domain.model.favorites.Favorite
import com.example.jetgames.core.domain.repo.FavoritesRepo
import com.example.jetgames.core.domain.repo.GameDetailRepo
import com.example.jetgames.core.domain.util.Resource
import com.example.jetgames.details.state.DetailsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: GameDetailRepo,
    private val savedStateHandle: SavedStateHandle,
    private val postExecutionThread: PostExecutionThread,
    private val favoritesRepo: FavoritesRepo,
) : ViewModel() {

    private val _game = MutableStateFlow<Resource<GameDetails>>(Resource.Loading())

    private val _screenShots = MutableStateFlow<List<String?>>(emptyList())

    private val _isGameLiked = MutableStateFlow(false)

    private val _detailsScreenState = MutableStateFlow(DetailsScreenState())

    val detailsScreenState: StateFlow<DetailsScreenState> get() = _detailsScreenState

    var id = -1

    init {
        viewModelScope.launch {
            combine(
                _game,
                _screenShots,
                _isGameLiked,
            ) { game, screenShots, isGameLiked ->

                DetailsScreenState(
                    game = game,
                    screenShots = screenShots,
                    isLiked = isGameLiked,
                )
            }
                .catch { throwable ->
                }
                .collect {
                    _detailsScreenState.value = it
                }
        }
    }

    fun setScreenshots(screenshots: List<String?>?) {
        if (!screenshots.isNullOrEmpty()) {
            _screenShots.value = screenshots
        }
    }

    fun fetchGame(id: Int) {
        viewModelScope.launch(postExecutionThread.main) {
            try {
                val gameDetail = withContext(postExecutionThread.io) { repo.game(id) }
                _game.value = Resource.Success(gameDetail)
            } catch (exception: Throwable) {
                _game.value = Resource.Error(exception.localizedMessage ?: "Something went wrong")
            }
        }
    }

    fun addToFavorites(favorite: Favorite) {
        setLiked(true)
        viewModelScope.launch(postExecutionThread.io) {
            favoritesRepo.upsert(favorite)
        }
    }

    fun removeFromFavorites(favorite: Favorite) {
        setLiked(false)
        viewModelScope.launch(postExecutionThread.io) {
            favoritesRepo.delete(favorite)
        }
    }

    fun checkFavorites(id: Int) {
        viewModelScope.launch(postExecutionThread.main) {
            _isGameLiked.value =
                withContext(postExecutionThread.io) { favoritesRepo.favorite(id) }.id >= 0
        }
    }

    private fun setLiked(isLiked: Boolean) {
        _isGameLiked.value = isLiked
    }

    fun getIcon(game: GameDetails): String? {
        val rating = game.ratings?.maxByOrNull { it?.percent!! }
        return rating?.icon
    }
}
