package com.application.movielist.data

import kotlinx.serialization.Serializable

@Serializable
data class MovieData(
    val kinopoiskId: Int,
    val posterUrl: String,
    val ratingAgeLimits: String,
    val genres: List<Genre>,
    val ratingKinopoisk: Float,
    val ratingKinopoiskVoteCount: Int,
    val nameRu: String,
    val filmLength: Int
)

@Serializable
data class MovieInfo(
    val kinopoiskId: Int,
    val posterUrl: String,
    val nameRu: String,
    val genres: List<Genre>,
    val ratingKinopoiskVoteCount: Int,
    val ratingAgeLimits: String,
    val shortDescription: String,
    val footage: List<Footage>
)

@Serializable
data class Genre(
    val genre: String
)

@Serializable
data class Footage(
    val previewUrl: String
)

@Serializable
data class MoviesList(
    val result: List<MovieData>
)

@Serializable
data class FootageList(
    val pictures: List<Footage>
)