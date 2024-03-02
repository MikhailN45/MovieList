package com.application.movielist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM MOVIE_DATA ORDER BY id ASC")
    fun getMoviesFromDb(): List<MovieDbEntity>

    @Insert
    fun addMoviesToDb(movieDbEntity: MovieDbEntity)
}