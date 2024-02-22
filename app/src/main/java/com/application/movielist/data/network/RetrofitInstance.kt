package com.application.movielist.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://kinopoiskapiunofficial.tech"

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .client(okHttp)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttp = OkHttpClient
        .Builder()
        .addInterceptor(logger)
        .build()

    val api: KinopoiskApiResponse by lazy {
        retrofit.create(KinopoiskApiResponse::class.java)
    }
}
