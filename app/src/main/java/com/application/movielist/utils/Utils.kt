package com.application.movielist.utils

import com.application.movielist.R
import com.application.movielist.data.Genre
import com.application.movielist.data.MovieData
import com.application.movielist.data.MovieInfo

object Utils {

    fun getTags(genres: List<Genre>): String = genres.joinToString(", ") { it.genre }

    fun getRating(rating: Any): Float {
        val ratingBarNumber: Float = when (rating) {
            (rating is Float) -> rating.toString().toFloat() / 2
            (rating is String) -> rating.toString().dropLast(1).toFloat() / 20
            else -> 0F
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

    fun getAgeRatingImg(minimumAge: String?): Int =
        if (minimumAge == "age16") R.drawable.rating16 else R.drawable.rating13

    fun getRatingStringInt(minimumAge: String): String {
        return if (minimumAge == "age16") "16+" else "13+"
    }
}
