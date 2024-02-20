package com.application.movielist.repository

import com.application.movielist.data.MoviesResponse
import com.application.movielist.data.network.RetrofitInstance

class Repository {
    suspend fun getActualMovies(): MoviesResponse {
        return RetrofitInstance.api.getActualMovies()
    }
}