package com.application.movielist.data
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
    val actors: List<ActorData>,
    val isLiked: Boolean = false
)

data class GenreData(
    val id: Int,
    val genre: String
)

data class ActorData(
    val personId: Int,
    val nameRu: String,
    val posterUrl: String
)