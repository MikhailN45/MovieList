package com.application.movielist.utils

import com.application.movielist.R
import com.application.movielist.data.GenreData

object Utils {

    fun getTags(genres: List<GenreData>): String = genres.joinToString(", ") { it.genre }

    fun getAgeRatingImg(minimumAge: String): Int =
        if (minimumAge == "age16") R.drawable.rating16 else R.drawable.rating13

    fun getRatingStringInt(minimumAge: String): String {
        return if (minimumAge == "age16") "16+" else "13+"
    }
}
