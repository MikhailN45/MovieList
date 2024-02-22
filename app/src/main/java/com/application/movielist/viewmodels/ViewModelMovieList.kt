package com.application.movielist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.movielist.domain.interactors.MovieInteractor
import com.application.movielist.states.MovieListUiState
import kotlinx.coroutines.launch
import kotlin.Exception

class ViewModelMovieList(private val interactor: MovieInteractor) : ViewModel() {
    private val _state = MutableLiveData<MovieListUiState>()
    val state: LiveData<MovieListUiState> = _state

    init {
        getMovies()
    }

    fun getMovies() {

        viewModelScope.launch {
            _state.value = MovieListUiState.Loading
            try {
                val movies = interactor.getActualMovies().movies
                _state.value = MovieListUiState.Success(movies)
            } catch (exception: Exception) {
                _state.value = MovieListUiState.Error
            }
        }
    }
}