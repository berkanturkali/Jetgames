package com.example.jetgames.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.jetgames.core.domain.model.navargs.ScreenshotsArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenshotsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _screenshotsAndPage = MutableLiveData<ScreenshotsArgs>()

    val screenshotsAndPage: LiveData<ScreenshotsArgs> get() = _screenshotsAndPage

    init {
        savedStateHandle.get<ScreenshotsArgs>("screenshotsArgs")?.let {
            _screenshotsAndPage.value = it
        }
    }
}
