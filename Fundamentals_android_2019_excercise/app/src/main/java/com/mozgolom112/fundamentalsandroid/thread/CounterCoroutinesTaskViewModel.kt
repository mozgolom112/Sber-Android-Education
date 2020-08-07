package com.mozgolom112.fundamentalsandroid.thread

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class CounterCoroutinesTaskViewModel : ViewModel() {

    private var job: Job? = null

    private var coroutineScope: CoroutineScope? = null

    private val count_ = MutableLiveData<Int>(0)
    val count: LiveData<Int>
        get() = count_

    val isDone = MutableLiveData<Boolean>(false)
    val isJobNotBeCreatedError = MutableLiveData<Boolean>(false)
    val isCoroutineExistError = MutableLiveData<Boolean>(false)
    val isCoroutineCancelledError = MutableLiveData<Boolean>(false)

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun onCreateClick() {
        job = Job()
        coroutineScope = CoroutineScope(job as CompletableJob + Dispatchers.Main)
        Log.d("onCreateClick", "Coroutine was created")

    }

    fun onStartClick() {
        isDone.value = false

        val isJobCancelled = job?.isCancelled ?: true

        if (!isJobCancelled) {
            coroutineScope?.launch {
                repeat(10) {
                    backgroundWork()
                }
                workWasDone()
            } ?: Log.d("Error", "coroutineScope must be created before called")
        } else {
            isJobNotBeCreatedError.value = true
            Log.d("Error", "Job must be created before called")
        }
    }

    private suspend fun backgroundWork() {
        count_.value = count_.value?.plus(1)
        Log.d("start", count.value.toString())
        delay(500)
    }

    private fun workWasDone() {
        count_.value = 0
        isDone.value = true
    }

    fun onCancelClick() {

        if (coroutineScope != null) {
            val isCoroutineScopeActive = coroutineScope?.isActive ?: false

            if (isCoroutineScopeActive) {
                coroutineScope?.cancel()
                Log.d("onCancelClick", "${coroutineScope?.isActive}")
                Log.d("onCancelClick", "Coroutine was canceled")
            } else {
                isCoroutineCancelledError.value = true
                Log.d("onCancelClick", "Coroutine has already canceled")
            }
        } else {
            isCoroutineExistError.value = true
            Log.d("onCancelClick", "Coroutine is not exist")
        }


    }

    //Support func
    fun isDoneWasCalled() {
        //change on init state
        isDone.value = false
    }

    fun isJobNotBeCreatedErrorWasCalled() {
        isJobNotBeCreatedError.value = false
    }

    fun isCoroutineCancelledErrorWasCalled() {
        isCoroutineCancelledError.value = false
    }

    fun isCoroutineExistErrorWasCalled() {
        isCoroutineExistError.value = false
    }
}