package com.mozgolom112.fundamentalsandroid.support.utils

import com.mozgolom112.fundamentalsandroid.database.DatabaseMovie
import com.mozgolom112.fundamentalsandroid.database.DatabaseTrailer
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.domain.Trailer
import com.mozgolom112.fundamentalsandroid.network.NetworkMovie
import com.mozgolom112.fundamentalsandroid.network.NetworkMovieContainer
import com.mozgolom112.fundamentalsandroid.network.NetworkTrailer
import com.mozgolom112.fundamentalsandroid.network.NetworkTrailerContainer
import com.mozgolom112.fundamentalsandroid.support.IMG_BACKDROP_TMDB_BASE_URL
import com.mozgolom112.fundamentalsandroid.support.IMG_POSTER_TMDB_BASE_URL
import com.mozgolom112.fundamentalsandroid.support.YOUTUBE_BASE_URL


/**
 * Convert Network results to domain objects
 */
fun NetworkMovieContainer.asDomainModel(): List<Movie>? {
    return results?.map {
        castMovieFromNetwork(it)
    }
}

private fun castMovieFromNetwork(it: NetworkMovie): Movie {
    return Movie(
        id = it.id,
        title = it.title,
        overview = it.overview,
        poster_path = IMG_POSTER_TMDB_BASE_URL + it?.poster_path ?: null,
        backdrop_path = IMG_BACKDROP_TMDB_BASE_URL + it?.backdrop_path ?: null,
        release_date = it.release_date,
        popularity = it.popularity
    )
}

fun NetworkTrailerContainer.asDomainModel(): List<Trailer> ? {
    return results?.map {
        Trailer(
            movieId = this.id, //id of movie
            url = YOUTUBE_BASE_URL + it.key
        )
    }
}

fun NetworkMovieContainer.asDatabaseModel(): List<DatabaseMovie> {
    return results?.map {
        castDBMovieFromNetwork(it)
    }
}

private fun castDBMovieFromNetwork(it: NetworkMovie): DatabaseMovie {
    return DatabaseMovie(
        id = it.id,
        title = it.title,
        overview = it.overview,
        poster_path = IMG_POSTER_TMDB_BASE_URL + it?.poster_path ?: null,
        backdrop_path = IMG_BACKDROP_TMDB_BASE_URL + it?.backdrop_path ?: null,
        release_date = it.release_date,
        popularity = it.popularity
    )
}

fun List<DatabaseMovie>.asDomainModel(): List<Movie> {
    return map{
        castMovieFromDatabase(it)
    }
}

fun List<NetworkMovie>.asDatabaseModel(): List<DatabaseMovie> {
    return map{
        castDBMovieFromNetwork(it)
    }
}

private fun castMovieFromDatabase(it: DatabaseMovie): Movie {
    return Movie(
        id = it.id,
        title = it.title,
        overview = it.overview,
        poster_path = IMG_POSTER_TMDB_BASE_URL + it?.poster_path ?: null,
        backdrop_path = IMG_BACKDROP_TMDB_BASE_URL + it?.backdrop_path ?: null,
        release_date = it.release_date,
        popularity = it.popularity
    )
}

fun NetworkTrailerContainer.asDatabaseTrailer(): List<DatabaseTrailer>{
    return results?.map {
        DatabaseTrailer(
            movieId = this.id, //id of movie
            url = YOUTUBE_BASE_URL + it.key
        )
    }
}

fun Trailer.asDatabaseTrailer(): DatabaseTrailer {
    return DatabaseTrailer(
        movieId = movieId,
        url = url
    )
}

