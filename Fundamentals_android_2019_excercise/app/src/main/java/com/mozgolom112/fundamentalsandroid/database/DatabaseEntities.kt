package com.mozgolom112.fundamentalsandroid.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class DatabaseMovie(

    @PrimaryKey
    val id: Int,
    val title: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val overview: String,
    val release_date: String,
    var popularity: Double
)

@Entity(tableName = "trailers")
data class DatabaseTrailer (
    @PrimaryKey
    val movieId: Int,
    val url: String?
)