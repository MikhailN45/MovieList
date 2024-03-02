package com.application.movielist.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_data")
data class MovieDbEntity(
    @PrimaryKey val id: Long,
    @Embedded val details: MovieInfo
)