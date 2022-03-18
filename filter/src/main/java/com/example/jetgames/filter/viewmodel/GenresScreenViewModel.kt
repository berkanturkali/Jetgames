package com.example.jetgames.filter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.example.jetgames.core.domain.model.genres.Genre
import com.example.jetgames.core.domain.usecase.filters.GenresUseCase


class GenresScreenViewModel constructor(
    private val useCase: GenresUseCase,
) {

    private val _refresh = MutableLiveData<Boolean>()

    val platforms = Transformations.switchMap(_refresh) {
        liveData { useCase.execute(it).collect(::emit) }
    }

    init {
        setRefresh()
    }

    fun setRefresh() {
        _refresh.value = true
    }

    fun sortGenres(genres: List<Genre>) = genres.sortedBy(Genre::name)

}