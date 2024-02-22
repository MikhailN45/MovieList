package com.application.movielist.data.network

import com.application.movielist.data.Footage
import com.application.movielist.data.FootageList
import com.application.movielist.data.MovieInfo
import com.application.movielist.data.MoviesList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"

interface KinopoiskApiResponse {
    @Headers("x-api-key: $API_KEY")
    @GET("api/v2.2/films/top?type=TOP_100_POPULAR_FILMS")
    suspend fun getActualMovies(): MoviesList

    @Headers("x-api-key: $API_KEY")
    @GET("api/v2.2/films/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieInfo

    @Headers("x-api-key: $API_KEY")
    @GET("api/v2.2/films/{id}/images?type=STILL&page=1")
    suspend fun getMovieFootage(@Path("id") id: Int): FootageList
}