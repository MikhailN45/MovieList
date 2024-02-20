package com.application.movielist.data.network

import com.application.movielist.domain.mappers.toFootage
import com.application.movielist.domain.mappers.toMovies
import com.application.movielist.domain.mappers.toMovieInfo
import com.application.movielist.domain.model.Footage
import com.application.movielist.domain.model.MovieInfo
import com.application.movielist.domain.model.Movies

class AppRepositoryImpl(private val api: KinopoiskApiResponse) : AppRepository {
    override suspend fun getActualMovies(): Movies =
        api.getActualMovies().toMovies()

    override suspend fun getFilmDetails(id: Int): MovieInfo =
        api.getMovieDetails(id).toMovieInfo()

    override suspend fun getMovieFootage(id: Int): Footage =
        api.getMovieFootage(id).toFootage()

}
