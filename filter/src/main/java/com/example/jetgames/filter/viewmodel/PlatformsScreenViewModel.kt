package com.example.jetgames.filter.viewmodel

import androidx.lifecycle.*
import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.core.domain.usecase.filters.PlatformsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class PlatformsScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val platformsUseCase: PlatformsUseCase,
) : ViewModel() {

    private val _refresh = MutableLiveData<Boolean>()

    val platforms = Transformations.switchMap(_refresh) {
        liveData { platformsUseCase.execute(it).collectLatest(::emit) }
    }

    init {
        setRefresh()
    }

    fun setRefresh() {
        _refresh.value = true
    }

    fun sortPlatforms(platforms: List<Platform>) = platforms.sortedBy(Platform::name)

    fun isApplyButtonActive(items: List<Platform>, selectedPlatforms: List<Platform>) =
        items.sortedBy(Platform::id) != selectedPlatforms.sortedBy(Platform::id)
}
