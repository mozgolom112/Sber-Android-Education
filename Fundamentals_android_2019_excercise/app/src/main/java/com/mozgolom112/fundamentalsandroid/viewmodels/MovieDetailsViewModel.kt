package com.mozgolom112.fundamentalsandroid.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mozgolom112.fundamentalsandroid.database.DatabaseTMDB
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.network.TMDB
import com.mozgolom112.fundamentalsandroid.repository.cache.MoviesCache
import com.mozgolom112.fundamentalsandroid.repository.cache.SharedPreferencesImpl
import com.mozgolom112.fundamentalsandroid.repository.repositories.MovieRepository
import com.mozgolom112.fundamentalsandroid.support.utils.asDomainModel
import kotlinx.coroutines.*

class MovieDetailsViewModel(val movie: Movie,private val context: Context) : ViewModel() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    private val database
            by lazy(Dispatchers.IO) { DatabaseTMDB.getInstance(context) }
    private val cache
            by lazy(Dispatchers.IO) {
                MoviesCache(
                    database.movieDao,
                    database.trailerDao,
                    SharedPreferencesImpl(context)
                )
            }
    private val movieRepository by lazy(Dispatchers.IO) { MovieRepository(TMDB.TMDBApi, cache) }

    val trailerUrl = MutableLiveData<String>("")
    val isTrailerFound = MutableLiveData<Boolean>(false)

    init {
        if (movie != null) {
            coroutineScope.launch {
                try {
                    val resultUrl = withContext(Dispatchers.IO){movieRepository.getMovieTrailerUrl(movie.id)}
                    trailerUrl.value = resultUrl
                    isTrailerFound.value = true
                }
                catch (e: Throwable){
                    Log.e("Trailer URL Error", e.message)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun getTrailerUrl(): String? = trailerUrl.value
}