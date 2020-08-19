package com.mozgolom112.fundamentalsandroid.viewmodels.viewmodelsfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mozgolom112.fundamentalsandroid.repository.cache.MoviesCache
import com.mozgolom112.fundamentalsandroid.viewmodels.MovieViewModel

class MovieViewModelFactory (private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}