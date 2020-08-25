package com.mozgolom112.fundamentalsandroid.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mozgolom112.fundamentalsandroid.database.DatabaseTMDB
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.network.NetworkMovieContainer
import com.mozgolom112.fundamentalsandroid.network.TMDB
import com.mozgolom112.fundamentalsandroid.network.TMDB.TMDBApi
import com.mozgolom112.fundamentalsandroid.repository.cache.MoviesCache
import com.mozgolom112.fundamentalsandroid.repository.cache.SharedPreferencesImpl
import com.mozgolom112.fundamentalsandroid.repository.repositories.MovieRepository
import com.mozgolom112.fundamentalsandroid.support.utils.asDomainModel
import kotlinx.coroutines.*

//TODO("Remove context from constructor")
class MovieViewModel(context: Context) : ViewModel() {

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
    private val movieRepository: MovieRepository by lazy(Dispatchers.IO) { MovieRepository(TMDBApi, cache) }

    val movies = MutableLiveData<List<Movie>>()
    private val currentPage = MutableLiveData<Int>(1)
    val isLoadState = MutableLiveData<Boolean>(false)

    init {
        loadFirstPage()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    private fun loadFirstPage() {
        if (movies.value == null) {
            coroutineScope.launch {
                try {
                    Log.i("getPopularMovies", "Start")
                    movies.value =
                        withContext(Dispatchers.IO) { movieRepository.getPopularMovies() }
                    Log.i("getPopularMovies", "Has result")
                } catch (e: Throwable) {
                    Log.e("getPopularMoviesError", e.message)
                }
            }
        }
    }

    fun loadNextPageOfMovies() {
        Log.i("And of list", "Hello there")
        //TODO("Remove buffer")
        val temp = movies.value as MutableList<Movie>
        currentPage.value = currentPage.value?.plus(1)
        var page = currentPage?.value ?: 1
        coroutineScope.launch {
            isLoadState.value = true
            try {
                val result = movieRepository.getPopularMovies(page)
                Log.i("getPopularMovies", "Has result")
                temp.addAll(result)
                movies.postValue(temp)
                isLoadState.value = false
            } catch (e: Throwable) {
                Log.e("loadNextPageOfMoviesError", e.message)
                currentPage.value?.minus(1)
                isLoadState.value = false
            }
        }
    }
}