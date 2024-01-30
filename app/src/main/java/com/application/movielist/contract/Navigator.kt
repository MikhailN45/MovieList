package com.application.movielist.contract

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {
    fun goMovieDetailsScreen()

    fun goBack()
}