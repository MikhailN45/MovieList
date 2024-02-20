package com.application.movielist.domain.interactors

import com.application.movielist.domain.model.Footage
import com.application.movielist.domain.model.MovieInfo
import com.application.movielist.domain.model.Movies

interface MovieInteractor {
    suspend fun getActualMovies(): Movies

    suspend fun getFilmDetails(id: Int): MovieInfo

    suspend fun getMovieFootage(id: Int): Footage
}