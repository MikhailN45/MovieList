package com.application.movielist.domain.model

data class Movies(
    val movies: List<MovieData>
)

data class MovieData(
    val filmId: Int,
    val nameRu: String,
    val posterUrl: String,
    val ratingKinopoisk: Float,
    val ratingKinopoiskVoteCount: Int,
    val ratingAgeLimits: String,
    val filmLength: Int,
    val genres: List<GenreData>,
    val isLiked: Boolean = false
)

data class MovieInfo(
    val kinopoiskId: Int,
    val nameRu: String,
    val coverUrl: String,
    val ratingKinopoiskVoteCount: Int,
    val ratingAgeLimits: String,
    val shortDescription: String,
    val genres: List<GenreData>
)

data class Footage(
    val footage: List<FootageData>
)

data class FootageData(
    val previewUrl: String
)

data class GenreData(
    val genre: String
)