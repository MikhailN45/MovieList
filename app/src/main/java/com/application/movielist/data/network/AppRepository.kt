package com.application.movielist.data.network

import com.application.movielist.domain.model.Footage
import com.application.movielist.domain.model.MovieInfo
import com.application.movielist.domain.model.Movies

interface AppRepository {
    suspend fun getActualMovies(): Movies

    suspend fun getFilmDetails(id: Int): MovieInfo

    suspend fun getMovieFootage(id: Int): Footage
}