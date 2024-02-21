package com.application.movielist.states

import com.application.movielist.domain.model.MovieData

sealed interface MovieListUiState {
    data object Loading : MovieListUiState
    data class Success(val films: List<MovieData>) : MovieListUiState
    data object Error : MovieListUiState
}
