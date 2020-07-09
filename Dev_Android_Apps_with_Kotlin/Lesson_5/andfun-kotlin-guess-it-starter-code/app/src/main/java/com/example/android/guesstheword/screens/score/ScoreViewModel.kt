package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int = 0) : ViewModel() {

    private val _score by lazy { MutableLiveData<Int>() }
    val score: LiveData<Int>
        get() = _score

    private val _eventPlayAgain by lazy { MutableLiveData<Boolean>() }
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain

    init{
        _score.value = finalScore
        _eventPlayAgain.value = false
    }

    fun onPlayButton(){
        _eventPlayAgain.value = true
    }
}