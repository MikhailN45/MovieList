package com.application.movielist.data

data class MoviesResponse(
    val movies: List<MovieDataResponse>
)

data class MovieDataResponse(
    val filmId: Int,
    val nameRu: String,
    val posterUrl: String,
    val ratingKinopoisk: Float,
    val ratingKinopoiskVoteCount: Int,
    val ratingAgeLimits: String,
    val filmLength: Int,
    val genres: List<GenreDataResponse>,
    val isLiked: Boolean = false
)

data class MovieInfoResponse(
    val kinopoiskId: Int,
    val nameRu: String,
    val coverUrl: String,
    val ratingKinopoiskVoteCount: Int,
    val ratingAgeLimits: String,
    val shortDescription: String,
    val genres: List<GenreDataResponse>
)

data class FootageResponse(
    val footage: List<FootageDataResponse>
)

data class FootageDataResponse(
    val previewUrl: String
)

data class GenreDataResponse(
    val genre: String
)

