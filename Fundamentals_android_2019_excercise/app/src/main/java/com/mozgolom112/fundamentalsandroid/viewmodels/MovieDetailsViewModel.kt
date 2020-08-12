package com.mozgolom112.fundamentalsandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.network.TMDB
import com.mozgolom112.fundamentalsandroid.support.utils.asDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieDetailsViewModel(val movie: Movie) : ViewModel() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    val trailerUrl = MutableLiveData<String>("")

    init {
        if (movie != null) {
            coroutineScope.launch {
                val listResult = TMDB.TMDBApi.getMovieTrailer(movie_id = movie.id).await()
                val results = listResult?.asDomainModel() ?: emptyList()
                if (results.isNotEmpty()){
                    trailerUrl.value = results[0].url
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