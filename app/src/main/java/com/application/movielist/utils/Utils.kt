package com.application.movielist.utils

import com.application.movielist.R
import com.application.movielist.data.GenreData

object Utils {
    fun getTags(genres: List<GenreData>): String = genres.joinToString(", ") { it.name }

    fun getAgeRatingImg(minimumAge: Int): Int =
        if (minimumAge >= 16) R.drawable.rating16 else R.drawable.rating13
}
