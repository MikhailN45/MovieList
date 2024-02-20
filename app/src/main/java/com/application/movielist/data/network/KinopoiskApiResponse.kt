package com.application.movielist.data.network

import com.application.movielist.data.FootageResponse
import com.application.movielist.data.MovieInfoResponse
import com.application.movielist.data.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"

interface KinopoiskApiResponse {
    @Headers("x-api-key: $API_KEY")
    @GET("api/v2.2/films/top?type=TOP_100_POPULAR_FILMS")
    suspend fun getActualMovies(): MoviesResponse

    @Headers("x-api-key: $API_KEY")
    @GET("api/v2.2/films/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int) : MovieInfoResponse

    @Headers("x-api-key: $API_KEY")
    @GET("api/v2.2/films/{id}/images?type=STILL&page=1")
    suspend fun getMovieFootage(@Path("id") id: Int): FootageResponse
}