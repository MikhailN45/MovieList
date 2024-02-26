package com.application.movielist.repository

import com.application.movielist.data.FootageList
import com.application.movielist.data.MovieInfo
import com.application.movielist.data.MoviesList
import com.application.movielist.data.network.RetrofitInstance

class Repository {
    suspend fun getActualMovies(): MoviesList = RetrofitInstance.api.getActualMovies()

    suspend fun getMovieDetails(id: Int): MovieInfo = RetrofitInstance.api.getMovieDetails(id)

    suspend fun getMovieFootage(id: Int): FootageList = RetrofitInstance.api.getMovieFootage(id)
}