package com.mozgolom112.fundamentalsandroid.thread

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class SimpleAsyncTask : ViewModel() {

    val progress = MutableLiveData<Int>()

    private var mBackgroundThread: Thread? = null

    //flags

    val isCancelled = MutableLiveData<Boolean>(false)

    val isOnPreExecutorCalled = MutableLiveData<Boolean>(false)

    val isOnPostExecutorCalled = MutableLiveData<Boolean>(false)

    fun onPreExecute(){
        isOnPreExecutorCalled.value = true
    }

    fun execute(){
        runOnUiThread(Runnable {
            onPreExecute()
            mBackgroundThread = object : Thread("Handler_executor_thread") {
                override fun run() {
                    doInBackground()
                    runOnUiThread(Runnable { onPostExecute() })
                }
            }
            mBackgroundThread?.start()
        })
    }

    fun doInBackground()  {
        val end = 10
        for (i in 0..end) {
            if (isCancelled.value == true) {
                return
            }

            publishProgress(i)
            SystemClock.sleep(500)
        }
    }

    private fun publishProgress(progress: Int) {
        runOnUiThread(Runnable { onProgressUpdate(progress) })
    }

    private fun runOnUiThread(runnable: Runnable) {
        Handler(Looper.getMainLooper()).post(runnable)
    }

    fun onPostExecute() {
        isOnPostExecutorCalled.value = true
    }

    fun onProgressUpdate(currrentProgress: Int) {
        progress.value = currrentProgress
    }

    fun cancel() {
        isCancelled.value = true
        if (mBackgroundThread != null) {
            mBackgroundThread?.interrupt()
        }
    }

    //support func
    fun isOnPreExecutorWasCalled(){
        isOnPreExecutorCalled.value = false
    }

    fun isOnPostExecutorWasCalled(){
        isOnPostExecutorCalled.value = false
    }
}