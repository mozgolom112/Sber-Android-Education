package com.mozgolom112.fundamentalsandroid.support.utils

import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.domain.Trailer
import com.mozgolom112.fundamentalsandroid.network.NetworkMovieContainer
import com.mozgolom112.fundamentalsandroid.network.NetworkTrailerContainer
import com.mozgolom112.fundamentalsandroid.support.IMG_BACKDROP_TMDB_BASE_URL
import com.mozgolom112.fundamentalsandroid.support.IMG_POSTER_TMDB_BASE_URL
import com.mozgolom112.fundamentalsandroid.support.YOUTUBE_BASE_URL


/**
 * Convert Network results to domain objects
 */
fun NetworkMovieContainer.asDomainModel(): List<Movie>? {
    return results?.map {
        Movie(
            id = it.id,
            title = it.title,
            overview = it.overview,
            poster_path = IMG_POSTER_TMDB_BASE_URL + it?.poster_path ?: null,
            backdrop_path = IMG_BACKDROP_TMDB_BASE_URL + it?.backdrop_path ?: null,
            release_date = it.release_date)
    }
}

fun NetworkTrailerContainer.asDomainModel(): List<Trailer> ? {
    return results?.map {
        Trailer(
            url = YOUTUBE_BASE_URL + it.key
        )
    }
}