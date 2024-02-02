package com.application.movielist.data
data class MovieData(
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val minimumAge: Int,
    val runtime: Int,
    val genres: List<GenreData>,
    val actors: List<ActorData>,
    val isLiked: Boolean
)