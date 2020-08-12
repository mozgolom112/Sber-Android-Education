package com.mozgolom112.fundamentalsandroid.viewmodels.viewmodelsfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.viewmodels.MovieDetailsViewModel

class MovieDetailsViewModelFactory (private val movie: Movie) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}