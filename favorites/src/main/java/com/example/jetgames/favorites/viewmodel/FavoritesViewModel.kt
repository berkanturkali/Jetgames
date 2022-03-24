package com.example.jetgames.favorites.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jetgames.core.domain.repo.FavoritesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repo:FavoritesRepo
):ViewModel() {


}