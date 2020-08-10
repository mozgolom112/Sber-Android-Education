package com.mozgolom112.fundamentalsandroid.support.utils

import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.network.NetworkMovieContainer


/**
 * Convert Network results to domain objects
 */
fun NetworkMovieContainer.asDomainModel(): List<Movie>? {
    return movies?.map {
        Movie(
            id = it.id,
            title = it.title,
            overview = it.overview,
            poster_path = it.poster_path,
            backdrop_path = it.backdrop_path,
            release_date = it.release_date)
    }
}