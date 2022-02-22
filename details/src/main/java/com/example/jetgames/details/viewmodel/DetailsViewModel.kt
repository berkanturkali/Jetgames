package com.example.jetgames.details.viewmodel

import androidx.lifecycle.*
import com.example.jetgames.core.domain.executor.abstraction.PostExecutionThread
import com.example.jetgames.core.domain.model.detail.GameDetails
import com.example.jetgames.core.domain.repo.GameDetailRepo
import com.example.jetgames.core.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo:GameDetailRepo,
    private val savedStateHandle:SavedStateHandle,
    private val postExecutionThread: PostExecutionThread
):ViewModel() {

    private val _game = MutableLiveData<Resource<GameDetails>>()

    val game:LiveData<Resource<GameDetails>> get() = _game

    init {
        savedStateHandle.get<Int>("id")?.let{
            game(it)
        }
    }

    private fun game(id:Int){
        _game.value = Resource.Loading()
        try{
          viewModelScope.launch(postExecutionThread.main){
              val gameDetail = withContext(postExecutionThread.io){repo.game(id)}
              _game.value = Resource.Success(gameDetail)
          }
        }catch (exception:Throwable){
            _game.value = Resource.Error(exception.localizedMessage ?: "Something went wrong")
        }
    }
}