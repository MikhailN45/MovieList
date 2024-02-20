package com.application.movielist.data.network

import com.application.movielist.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttp = OkHttpClient
        .Builder()
        .addInterceptor(logger)
        .build()

    private val retrofit by lazy {
        Retrofit
            .Builder()
            .client(okHttp)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: KinopoiskApiResponse by lazy {
        retrofit.create(KinopoiskApiResponse::class.java)
    }
}
