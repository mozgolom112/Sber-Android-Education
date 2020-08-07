package com.mozgolom112.fundamentalsandroid.thread

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class CounterCoroutinesTaskViewModel : ViewModel() {

    private lateinit var job: Job

    private var coroutineScope: CoroutineScope = CoroutineScope(job + Dispatchers.Main)

    private val count_ = MutableLiveData<Int>(0)
    val count: LiveData<Int>
        get() = count_

    val isDone = MutableLiveData<Boolean>(false)

    fun onCreateClick(){
        job = Job()
        coroutineScope = CoroutineScope(job + Dispatchers.Main)
        Log.d("onCreateClick", "Coroutine was created")

    }

    fun onStartClick(){
        isDone.value = false
        coroutineScope?.launch {
            repeat(10){
                backgroundWork()
            }
            workWasDone()
        }
    }

    private suspend fun backgroundWork() {
        count_.value = count_.value?.plus(1)
        Log.d("start", count.value.toString())
        delay(500)
    }

    private fun workWasDone() {
        isDone.value = true
        count_.value = 0
    }

    fun onCancelClick(){
        coroutineScope?.cancel()
        Log.d("onCancelClick", "Coroutine was canceled")
    }
}