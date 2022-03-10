package com.example.jetgames.filter.viewmodel

import androidx.lifecycle.*
import com.example.jetgames.core.domain.model.platforms.Platform
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlatformsScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _refresh = MutableLiveData<Boolean>()

    val platforms = Transformations.switchMap(_refresh){
        liveData<List<Platform>> {  }
    }

    init {
        setRefresh()
    }


    fun setRefresh(){
        _refresh.value = true
    }
}