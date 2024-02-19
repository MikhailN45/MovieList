package com.application.movielist.data.network

import com.application.movielist.data.FootageData
import com.application.movielist.data.GenreData
import com.application.movielist.data.MovieData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
private val api = Retrofit.service

@ExperimentalSerializationApi
private suspend fun getActualMovies(): MovieData = api.getActualMovies()

@ExperimentalSerializationApi
private suspend fun getMovieDetails(movieId: Int): MovieData = api.getMovieDetails(movieId)

@ExperimentalSerializationApi
private suspend fun getMovieFootage(movieId: Int): FootageData = {
    return api.getMovieFootage(movieId)
}

@ExperimentalSerializationApi
suspend fun getMoviesList(): List<MovieData> = withContext(Dispatchers.IO) {
    return@withContext getActualMovies().results.map {
        MovieData(
            kinopoiskId =,
            nameRu =,
            shortDescription =,
            posterUrl =,
            coverUrl =,
            ratingKinopoisk =,
            ratingKinopoiskVoteCount =,
            ratingAgeLimits =,
            filmLength =,
            genres =,
            footage =
        )
    }
}