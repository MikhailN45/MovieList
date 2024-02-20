package com.application.movielist.domain.mappers

import com.application.movielist.data.FootageDataResponse
import com.application.movielist.data.FootageResponse
import com.application.movielist.data.GenreDataResponse
import com.application.movielist.data.MovieDataResponse
import com.application.movielist.data.MovieInfoResponse
import com.application.movielist.data.MoviesResponse
import com.application.movielist.domain.model.Footage
import com.application.movielist.domain.model.FootageData
import com.application.movielist.domain.model.GenreData
import com.application.movielist.domain.model.MovieData
import com.application.movielist.domain.model.MovieInfo
import com.application.movielist.domain.model.Movies


fun MovieDataResponse.toMovieData() = MovieData(
    filmId = filmId,
    nameRu = nameRu,
    posterUrl = posterUrl,
    ratingKinopoisk = ratingKinopoisk,
    ratingKinopoiskVoteCount = ratingKinopoiskVoteCount,
    ratingAgeLimits = ratingAgeLimits,
    filmLength = filmLength,
    genres = genres.toGenreList(),
    isLiked = isLiked
)

fun MovieInfoResponse.toMovieInfo() = MovieInfo(
    kinopoiskId = kinopoiskId,
    nameRu = nameRu,
    coverUrl = coverUrl,
    ratingKinopoiskVoteCount = ratingKinopoiskVoteCount,
    ratingAgeLimits = ratingAgeLimits,
    shortDescription = shortDescription,
    genres = genres.toGenreList()
)

fun FootageResponse.toFootage() = Footage(
    footage = footage.toFootageList()
)

fun GenreDataResponse.toGenre() = GenreData(genre = genre)

fun List<GenreDataResponse>.toGenreList(): List<GenreData> = map { it.toGenre() }

fun FootageDataResponse.toFootage() = FootageData(previewUrl = previewUrl)

fun List<FootageDataResponse>.toFootageList(): List<FootageData> = map { it.toFootage() }

fun MoviesResponse.toMovies() = Movies(movies = movies.toMoviesList())

fun List<MovieDataResponse>.toMoviesList(): List<MovieData> = map { it.toMovieData() }

