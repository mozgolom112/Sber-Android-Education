package com.mozgolom112.fundamentalsandroid.repository.repositories

import android.util.Log
import com.mozgolom112.fundamentalsandroid.database.DatabaseTMDB
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.network.TMDBInterface
import com.mozgolom112.fundamentalsandroid.repository.cache.MoviesCache
import com.mozgolom112.fundamentalsandroid.support.utils.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(
    private val tmdb: TMDBInterface,
    private val cache: MoviesCache
) {


    suspend fun getPopularMovies(): List<Movie> {
        if (cache.isCached()) {
            return cache.getMovies()
        }

        val networkMovies = tmdb.getPopularMovies().await()
        val movies = networkMovies.asDomainModel()

        cache.insertMovies(networkMovies.results)

        return movies ?: emptyList()
    }

    suspend fun getMovieTrailerUrl(movieId: Int): String {
        val cachedUrl = cache.getMovieTrailerUrl(movieId)

        if (cachedUrl != null) {
            return cachedUrl
        }

        val trailerContainer = tmdb.getMovieTrailer(movieId).await()
        val trailer = trailerContainer.asDomainModel()?.get(0) ?: null
        val url = trailer?.url ?: ""

        if (trailer != null) cache.insertTrailer(trailer)
        return url
    }

    suspend fun getPopularMovies(page: Int): List<Movie>{
        val networkMovies = tmdb.getPopularMovies(page = page).await()
        Log.i("GetPopularMovies","Start load into db")
        withContext(Dispatchers.IO) { cache.insertNewPage(networkMovies.results)}

        return networkMovies.asDomainModel() ?: emptyList()
    }

    fun deleteCachedData() {
        cache.clearCache()
    }
}