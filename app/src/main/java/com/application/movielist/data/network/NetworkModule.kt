package com.application.movielist.data.network

import com.application.movielist.data.MovieData
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

const val BASE_URL = "https://kinopoiskapiunofficial.tech"

@ExperimentalSerializationApi
object Retrofit {
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }
    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttp = OkHttpClient
        .Builder()
        .addInterceptor(logger)
        .build()

    private val instance: Retrofit = Retrofit
        .Builder()
        .client(okHttp)
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val service: KinopoiskApiResponse = instance.create(KinopoiskApiResponse::class.java)

}
