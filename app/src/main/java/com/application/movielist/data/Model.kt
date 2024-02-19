package com.application.movielist.data

import kotlinx.serialization.Serializable

@Serializable
data class MovieData(
    val kinopoiskId: Int,
    val nameRu: String,
    val shortDescription: String,
    val posterUrl: String,
    val coverUrl: String,
    val ratingKinopoisk: Float,
    val ratingKinopoiskVoteCount: Int,
    val ratingAgeLimits: String,
    val filmLength: Int,
    val genres: List<GenreData>,
    val footage: List<FootageData>,
    val isLiked: Boolean = false
)

@Serializable
data class GenreData(
    val id: Int,
    val genre: String
)

@Serializable
data class FootageData(
    val previewUrl: String
)