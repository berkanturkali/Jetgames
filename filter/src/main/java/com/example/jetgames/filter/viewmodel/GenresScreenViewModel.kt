package com.example.jetgames.filter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.jetgames.core.domain.model.genres.Genre
import com.example.jetgames.core.domain.usecase.filters.GenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenresScreenViewModel @Inject constructor(
    private val useCase: GenresUseCase,
) : ViewModel() {

    private val _refresh = MutableLiveData<Boolean>()

    val genres = _refresh.switchMap {
        liveData {
            useCase(it).collect {
                emit(it)
            }
        }
    }

    init {
        setRefresh()
    }

    fun setRefresh() {
        _refresh.value = true
    }

    fun sortGenres(genres: List<Genre>) = genres.sortedBy(Genre::name)

    fun isApplyButtonActive(items: List<String>, selectedGenres: List<String>) =
        items.sorted() != selectedGenres.sorted()
}
