package com.mozgolom112.fundamentalsandroid.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.network.NetworkMovieContainer
import com.mozgolom112.fundamentalsandroid.network.TMDB
import com.mozgolom112.fundamentalsandroid.network.TMDB.TMDBApi
import com.mozgolom112.fundamentalsandroid.support.utils.asDomainModel
import kotlinx.coroutines.*

class MovieViewModel : ViewModel() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    val movies = MutableLiveData<List<Movie>>()

    init {
        if (movies.value == null) {
            //var getPopularMoviesDeferred = TMDBApi.getPopularMovies(api_key)
            coroutineScope.launch {
                //getMoviesFromNetwork()
                val listResult = TMDBApi.getPopularMovies().await()

                val result = listResult?.asDomainModel() ?: emptyList()
                if (result.isNotEmpty()) {
                    Log.i("getPopularMovies", "Has result")
                    movies.value = result
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    suspend fun getMoviesFromNetwork() = withContext(Dispatchers.IO) {
        //val container = TMDBApi.getPopularMovies()
    }
}