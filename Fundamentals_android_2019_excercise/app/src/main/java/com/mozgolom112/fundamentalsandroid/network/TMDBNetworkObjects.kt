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
    val release_date: String,
    @Json(name = "popularity")
    val popularity: Double
)
@JsonClass(generateAdapter = true)
data class NetworkTrailerContainer(
    @Json(name = "results")
    val results: List<NetworkTrailer>,
    @Json(name = "id")
    val id: Int
)

@JsonClass(generateAdapter = true)
data class NetworkTrailer(
    @Json(name = "key")
    val key: String
)