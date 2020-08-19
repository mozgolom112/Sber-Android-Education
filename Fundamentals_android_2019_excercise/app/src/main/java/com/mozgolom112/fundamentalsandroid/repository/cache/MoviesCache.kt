package com.mozgolom112.fundamentalsandroid.repository.cache

import com.mozgolom112.fundamentalsandroid.database.Dao.MovieDao
import com.mozgolom112.fundamentalsandroid.database.Dao.TrailerDao
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.domain.Trailer
import com.mozgolom112.fundamentalsandroid.network.NetworkMovie
import com.mozgolom112.fundamentalsandroid.network.NetworkTrailer
import com.mozgolom112.fundamentalsandroid.network.NetworkTrailerContainer
import com.mozgolom112.fundamentalsandroid.support.utils.asDatabaseModel
import com.mozgolom112.fundamentalsandroid.support.utils.asDatabaseTrailer
import com.mozgolom112.fundamentalsandroid.support.utils.asDomainModel

private const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

class MoviesCache(
    private val movieDao: MovieDao,
    private val trailerDao: TrailerDao,
    private val preferenceHelper: SharedPreferencesInterface
) : MoviesCacheInterface{
    override fun isCached(): Boolean {
        //TODO("Remove execution from db")
        return movieDao.getAll().isNotEmpty() && !isExpired()
    }

    private fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()

        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferenceHelper.lastCacheTime
    }


    override fun getMovies(): List<Movie> {
        return movieDao.getAll().asDomainModel()
    }

    override fun getMovieTrailerUrl(movieId: Int): String? {
        return trailerDao.getTrailerByMovieId(movieId)?.url
    }

    override fun insertMovies(movies: List<NetworkMovie>) {
        movieDao.deleteAll()
        movieDao.insertAll(movies.asDatabaseModel())
        
        updateCachedTime()
    }

    override fun insertNewPage(movies: List<NetworkMovie>) {

        movieDao.insertNewPage(movies.asDatabaseModel())

        updateCachedTime()
    }

    override fun insertTrailer(trailer: NetworkTrailerContainer) {
        val firstTrailer = trailer.asDatabaseTrailer()[0]
        trailerDao.insert(firstTrailer)
    }

    override fun insertTrailer(trailer: List<Trailer>) {
        trailerDao.insert(trailer[0].asDatabaseTrailer())
    }

    override fun insertTrailer(trailer: Trailer) {
        trailerDao.insert(trailer.asDatabaseTrailer())
    }

    override fun clearCache() {
        movieDao.deleteAll()
        trailerDao.deleteAll()
    }

    private fun updateCachedTime() {
        preferenceHelper.lastCacheTime = System.currentTimeMillis()
    }
}