package com.application.movielist.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun <T> T.toJson(): String = Gson().toJson(this)

inline fun <reified T> String.fromJson(): T = Gson().fromJson(this, T::class.java)
class Converters {
    @TypeConverter
    fun movieToJson(it: MovieInfo) = it.toJson()

    @TypeConverter
    fun movieFromJson(src: String): MovieInfo = src.fromJson()

    @TypeConverter
    fun genreToJson(it: List<Genre>) = it.toJson()

    @TypeConverter
    fun genreFromJson(src: String): List<Genre> =
        Gson().fromJson(src, object : TypeToken<List<Genre>>() {}.type)

    @TypeConverter
    fun footageToJson(it: List<Footage>) = it.toJson()

    @TypeConverter
    fun footageFromJson(src: String): List<Footage> =
        Gson().fromJson(src, object : TypeToken<List<Footage>>() {}.type)
}