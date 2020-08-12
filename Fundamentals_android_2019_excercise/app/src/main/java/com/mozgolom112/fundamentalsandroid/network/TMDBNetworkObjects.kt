package com.mozgolom112.fundamentalsandroid.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkMovieContainer(val results: List<NetworkMovie>)

@JsonClass(generateAdapter = true)
data class NetworkMovie(
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "poster_path")
    val poster_path: String,
    @Json(name = "backdrop_path")
    val backdrop_path: String?,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "release_date")
    val release_date: String
)
@JsonClass(generateAdapter = true)
data class NetworkTrailerContainer(val results: List<NetworkTrailer>)

@JsonClass(generateAdapter = true)
data class NetworkTrailer(val key: String)