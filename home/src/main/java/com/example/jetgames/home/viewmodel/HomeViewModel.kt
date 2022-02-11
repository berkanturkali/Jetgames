package com.example.jetgames.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.jetgames.core.domain.usecase.games.GamesUseCase

class HomeViewModel constructor(
    private val useCase:GamesUseCase
):ViewModel() {
    val games = useCase.execute().cachedIn(viewModelScope)
}