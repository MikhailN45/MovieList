package com.application.movielist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.movielist.domain.interactors.MovieInteractor
import com.application.movielist.states.MovieDetailsUiState
import kotlinx.coroutines.launch

class ViewModelMovieDetails(private val interactor: MovieInteractor) : ViewModel() {
    private val _state = MutableLiveData<MovieDetailsUiState>()
    val state: LiveData<MovieDetailsUiState> = _state

    private var movieId: Int = 0
        set(value) {
            field = value
            getMovie()
        }


    private fun getMovie() {
        viewModelScope.launch {
            _state.value = MovieDetailsUiState.Loading
            try {
                val filmInfo = interactor.getFilmDetails(movieId)
                MovieDetailsUiState.Success(filmInfo)
            } catch (exception: Exception) {
                _state.value = MovieDetailsUiState.Error
            }
        }
    }
}