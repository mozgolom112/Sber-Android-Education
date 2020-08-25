package com.mozgolom112.fundamentalsandroid.services

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


const val DELAY_TICK = 100L
const val MAX_PROGRESS = 100
const val DELAY_VALUE = DELAY_TICK * MAX_PROGRESS + 1000L

class HeavyWorkerManager {

    private var handler: Handler = Handler()
    private var runnable: Runnable? = null
    private var counter = 0
    private val progressUpdateService: MutableLiveData<Int> = MutableLiveData()

    init {
        initWork()
    }

    fun startWork() {
        runnable?.run { handler.post(this) }
    }

    fun resetProgress() {
        counter = 0
        showProgressNumber(counter)
    }

    fun finishedWork() {
        counter = 100
        showProgressNumber(counter)
    }

    fun onDestroy() {
        runnable?.run {
            handler.removeCallbacks(this)
        }
    }

    fun getProgressUpdaterService(): LiveData<Int> = progressUpdateService

    private fun initWork() {
        runnable = Runnable {
            showProgressNumber(foregroundProcess())

            runnable?.run {
                handler.postDelayed(this, DELAY_TICK)
            }
        }
    }

    private fun showProgressNumber(progress: Int) {
        progressUpdateService.postValue(progress)
    }

    private fun foregroundProcess(): Int {
        return if (counter < MAX_PROGRESS) {
            Log.d("ForegroundProcess", "${counter}")
            counter++
        } else {
            counter
        }
    }
}
