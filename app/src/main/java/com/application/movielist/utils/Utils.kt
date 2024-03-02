package com.application.movielist.utils

import com.application.movielist.data.GenreResponse
import com.application.movielist.data.MovieDataResponse
import com.application.movielist.data.MovieInfoResponse

object Utils {
    fun getTags(genres: List<GenreResponse>): String = genres.take(2).joinToString(", ") {
        it.genre.replaceFirstChar(Char::titlecase)
    }

    fun getRating(rating: String): String {
        val ratingBarNumber: Float? = when {
            rating.isEmpty() -> null
            rating.contains("%") -> rating.dropLast(1).toFloat() / 10
            else -> rating.toFloat()
        }
        return ratingBarNumber.toString()
    }

    fun getReviews(movie: MovieDataResponse): String {
        val count = movie.ratingVoteCount
        val reviewsText = " REVIEWS"
        val emptyReviewsText = ""
        return if (count == 0) {
            emptyReviewsText
        } else
            "${count}${reviewsText}"
    }

    fun getReviewsForInfo(movie: MovieInfoResponse): String {
        val count = movie.ratingKinopoiskVoteCount
        val reviewsText = " REVIEWS"
        val emptyReviewsText = ""
        return if (count == 0) {
            emptyReviewsText
        } else
            "${count}${reviewsText}"
    }

    fun getAgeRating(minimumAge: String?): String {
        return if (minimumAge != null) {
            val ageRating: String = minimumAge.drop(3)
            ageRating.plus("+")
        } else ""


    }
}
