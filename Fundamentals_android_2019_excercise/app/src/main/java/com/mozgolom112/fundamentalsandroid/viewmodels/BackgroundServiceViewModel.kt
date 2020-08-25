package com.mozgolom112.fundamentalsandroid.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mozgolom112.fundamentalsandroid.dependency.Dependencies
import com.mozgolom112.fundamentalsandroid.services.HeavyWorkerManager

class BackgroundServiceViewModel(
    private val heavyWorkManager: HeavyWorkerManager
) : ViewModel() {

    val progressStatus: LiveData<Int> = heavyWorkManager.getProgressUpdaterService()


//    fun onStartIntentServiceClick() {
//        heavyWorkManager.startWork()
//    }
//
//    fun onStartServiceClick() {
//
//    }
}