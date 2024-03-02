package com.application.movielist.data.local

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieData(
    val filmId: Int,
    val posterUrl: String,
    val year: String,
    val genreResponses: List<Genre>,
    val rating: String,
    val ratingVoteCount: Int,
    val nameRu: String,
    val filmLength: String
)

@Serializable
data class MovieInfo(
    val kinopoiskId: Int,
    val posterUrl: String,
    val nameRu: String,
    val genreResponses: List<Genre>,
    val ratingKinopoiskVoteCount: Int,
    val ratingAgeLimits: String,
    val description: String,
    val footageResponse: List<Footage>
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
    @SerializedName("films")
    val result: List<MovieData>
)

@Serializable
data class FootageList(
    val items: List<Footage>
)