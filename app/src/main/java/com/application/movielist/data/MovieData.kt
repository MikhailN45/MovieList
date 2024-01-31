package com.application.movielist.data
data class MovieData(
    val poster: Int,
    val ageRating: Int,
    val like: Int,
    val title: String,
    val length: Int,
    val userRating: Int,
    val reviews: Int,
    val genres: List<String>
)