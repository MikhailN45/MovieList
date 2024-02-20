package com.application.movielist.domain.interactors

import com.application.movielist.data.network.AppRepository
import com.application.movielist.domain.model.Footage
import com.application.movielist.domain.model.MovieInfo
import com.application.movielist.domain.model.Movies

class MovieInteractorImpl(private val repository: AppRepository) : MovieInteractor {
    override suspend fun getActualMovies(): Movies =
        repository.getActualMovies()

    override suspend fun getFilmDetails(id: Int): MovieInfo =
        repository.getFilmDetails(id)

    override suspend fun getMovieFootage(id: Int): Footage =
        repository.getMovieFootage(id)
}