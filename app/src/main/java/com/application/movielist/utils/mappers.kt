package com.application.movielist.utils


import com.application.movielist.data.FootageResponse
import com.application.movielist.data.GenreResponse
import com.application.movielist.data.MovieDataResponse
import com.application.movielist.data.MovieInfoResponse
import com.application.movielist.data.MoviesListResponse
import com.application.movielist.data.local.Footage
import com.application.movielist.data.local.Genre
import com.application.movielist.data.local.MovieData
import com.application.movielist.data.local.MovieInfo
import com.application.movielist.data.local.MoviesList

fun MovieDataResponse.toMovieData() = MovieData(
    filmId = filmId,
    posterUrl = posterUrl,
    year = year,
    genreResponses = genreResponses.toGenreList(),
    rating = rating,
    ratingVoteCount = ratingVoteCount,
    nameRu = nameRu,
    filmLength = filmLength
)

fun MovieInfoResponse.toMovieInfo() = MovieInfo(
    kinopoiskId = kinopoiskId,
    posterUrl = posterUrl,
    nameRu = nameRu,
    genreResponses = genreResponses.toGenreList(),
    ratingKinopoiskVoteCount = ratingKinopoiskVoteCount,
    ratingAgeLimits = ratingAgeLimits,
    description = description,
    footageResponse = footageResponse.toFootageList()
)

fun GenreResponse.toGenre() = Genre(genre = genre)

fun List<GenreResponse>.toGenreList(): List<Genre> = map { it.toGenre() }

fun FootageResponse.toFootage() = Footage(previewUrl = previewUrl)

fun List<FootageResponse>.toFootageList(): List<Footage> = map { it.toFootage() }

fun MoviesListResponse.toMovies() = MoviesList(result = result.toMoviesList())

fun List<MovieDataResponse>.toMoviesList(): List<MovieData> = map { it.toMovieData() }
