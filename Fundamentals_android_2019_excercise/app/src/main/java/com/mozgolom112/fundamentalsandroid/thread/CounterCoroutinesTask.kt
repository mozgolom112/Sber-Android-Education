package com.mozgolom112.fundamentalsandroid.thread

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class CounterCoroutinesTask {

    private lateinit var job: Job

    private lateinit var coroutineScope: CoroutineScope

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
        coroutineScope.launch {
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
        if (coroutineScope == null) coroutineScope.cancel()
        Log.d("onCancelClick", "Coroutine was canceled")
    }
}