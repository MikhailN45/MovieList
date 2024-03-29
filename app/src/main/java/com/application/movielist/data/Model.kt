package com.application.movielist.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDataResponse(
    val filmId: Int,
    val posterUrl: String,
    val year: String,
    val genreResponses: List<GenreResponse>,
    val rating: String,
    val ratingVoteCount: Int,
    val nameRu: String,
    val filmLength: String
)

@Serializable
data class MovieInfoResponse(
    val kinopoiskId: Int,
    val posterUrl: String,
    val nameRu: String,
    val genreResponses: List<GenreResponse>,
    val ratingKinopoiskVoteCount: Int,
    val ratingAgeLimits: String,
    val description: String,
    val footageResponse: List<FootageResponse>
)

@Serializable
data class GenreResponse(
    val genre: String
)

@Serializable
data class FootageResponse(
    val previewUrl: String
)

@Serializable
data class MoviesListResponse(
    @SerializedName("films")
    val result: List<MovieDataResponse>
)

@Serializable
data class FootageListResponse(
    val items: List<FootageResponse>
)