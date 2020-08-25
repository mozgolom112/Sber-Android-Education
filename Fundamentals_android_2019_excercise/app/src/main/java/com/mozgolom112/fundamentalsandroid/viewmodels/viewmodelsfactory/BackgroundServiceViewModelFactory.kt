package com.mozgolom112.fundamentalsandroid.viewmodels.viewmodelsfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.services.HeavyWorkerManager
import com.mozgolom112.fundamentalsandroid.viewmodels.BackgroundServiceViewModel
import com.mozgolom112.fundamentalsandroid.viewmodels.MovieDetailsViewModel

class BackgroundServiceViewModelFactory (private val heavyWorkerManager: HeavyWorkerManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BackgroundServiceViewModel::class.java)) {
            return BackgroundServiceViewModel(heavyWorkerManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}