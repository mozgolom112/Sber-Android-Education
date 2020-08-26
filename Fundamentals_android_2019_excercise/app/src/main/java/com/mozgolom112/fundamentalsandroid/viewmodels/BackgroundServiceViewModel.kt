package com.mozgolom112.fundamentalsandroid.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.mozgolom112.fundamentalsandroid.dependency.Dependencies
import com.mozgolom112.fundamentalsandroid.services.HeavyWorkerManager
import com.mozgolom112.fundamentalsandroid.services.MAX_PROGRESS

class BackgroundServiceViewModel(
    private val heavyWorkManager: HeavyWorkerManager
) : ViewModel() {

    val isButtonsEnable = MutableLiveData<Boolean>() //enable/disable btns for start services
    val isEnableService = MutableLiveData<Boolean>()

    val isEnableDownloadService = MutableLiveData<Boolean>()
    val isEnableDownloadIntentService = MutableLiveData<Boolean>()
    val progressStatus = heavyWorkManager.getProgressUpdaterService()

    override fun onCleared() {
        super.onCleared()
        resetState()
    }

    fun resetState() {
        heavyWorkManager.run {
            onDestroy()
            resetProgress()
        }
    }

    fun onStopService(){
        isButtonsEnable.value = true
        isEnableService.value = true
        isEnableDownloadService.value = true
        isEnableDownloadIntentService.value = true
    }

    fun onStopIntentService(){
        isButtonsEnable.value = true
        isEnableDownloadIntentService.value = true
    }

    fun onStartServiceClick() {
        isButtonsEnable.value = false
        isEnableDownloadService.value = false
        isEnableService.value = false
    }

    fun onStartIntentServiceClick() {
        isButtonsEnable.value = false
        isEnableDownloadIntentService.value = false
        isEnableService.value = false
    }

}