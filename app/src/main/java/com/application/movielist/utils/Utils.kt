package com.application.movielist.utils

import com.application.movielist.data.Genre
import com.application.movielist.data.MovieData
import com.application.movielist.data.MovieInfo

object Utils {
    fun getTags(genres: List<Genre>): String = genres.take(2).joinToString(", ") {
        it.genre.replaceFirstChar(Char::titlecase)
    }

    fun getRating(rating: String): Float {
        val ratingBarNumber: Float = when {
            rating.isEmpty() -> 0F
            rating.contains("%") -> rating.dropLast(1).toFloat() / 20
            else -> rating.toFloat() / 2
        }
        return ratingBarNumber
    }

    fun getReviews(movie: MovieData): String {
        val count = movie.ratingVoteCount
        val reviewsText = " REVIEWS"
        val emptyReviewsText = ""
        return if (count == 0) {
            emptyReviewsText
        } else
            "${count}${reviewsText}"
    }

    fun getReviewsForInfo(movie: MovieInfo): String {
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
