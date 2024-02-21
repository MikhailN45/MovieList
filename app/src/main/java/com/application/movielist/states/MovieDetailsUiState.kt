package com.application.movielist.states

import com.application.movielist.domain.model.MovieInfo

sealed interface MovieDetailsUiState {
    data object Loading : MovieDetailsUiState
    data class Success(val movieInfo: MovieInfo) : MovieDetailsUiState
    data object Error : MovieDetailsUiState
}
