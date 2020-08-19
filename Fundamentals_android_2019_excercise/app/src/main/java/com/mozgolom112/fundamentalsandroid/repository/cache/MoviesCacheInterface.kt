package com.mozgolom112.fundamentalsandroid.repository.cache


import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.domain.Trailer
import com.mozgolom112.fundamentalsandroid.network.NetworkMovie
import com.mozgolom112.fundamentalsandroid.network.NetworkTrailer
import com.mozgolom112.fundamentalsandroid.network.NetworkTrailerContainer

interface MoviesCacheInterface {
    fun isCached(): Boolean

    fun getMovies(): List<Movie>

    fun getMovieTrailerUrl(movieId: Int): String?

    fun insertMovies(movies: List<NetworkMovie>)

    fun insertNewPage(movies: List<NetworkMovie>)

    fun insertTrailer(trailer: NetworkTrailerContainer)

    fun insertTrailer(trailer: List<Trailer>)

    fun insertTrailer(trailer: Trailer)

    fun clearCache()
}