package com.application.movielist.repository

import com.application.movielist.data.FootageListResponse
import com.application.movielist.data.MovieInfoResponse
import com.application.movielist.data.MoviesListResponse
import com.application.movielist.data.network.RetrofitInstance

class Repository {
    suspend fun getActualMovies(): MoviesListResponse = RetrofitInstance.api.getActualMovies()

    suspend fun getMovieDetails(id: Int): MovieInfoResponse = RetrofitInstance.api.getMovieDetails(id)

    suspend fun getMovieFootage(id: Int): FootageListResponse = RetrofitInstance.api.getMovieFootage(id)
}