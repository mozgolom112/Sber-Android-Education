package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    companion object {
        private const val DONE = 0L

        private const val ONE_SECOND = 1000L
        //TODO("Change 10000 to 60000 after testing")
        private const val COUNTDOWN_TIME = 10000L
    }

    private val timer: CountDownTimer by lazy {
        object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                Log.i("Check Timer", DateUtils.formatElapsedTime(millisUntilFinished / ONE_SECOND))
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _eventGameFinish.value = true
                Log.i("Check Timer", "Game finish")

            }
        }
    }

    private val _currentTime by lazy { MutableLiveData<Long>() }
    val currentTime : LiveData<Long>
        get() = _currentTime

    private val _word by lazy { MutableLiveData<String>() }
    val word: LiveData<String>
        get() = _word

    private val _score by lazy { MutableLiveData<Int>() }
    val score: LiveData<Int>
        get() = _score

    private val _eventGameFinish by lazy { MutableLiveData<Boolean>()}
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private lateinit var wordList: MutableList<String>

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    init {
        _eventGameFinish.value = false
        _currentTime.value = 0
        _word.value = ""
        _score.value = 0
        resetList()
        nextWord()

        timer.start()

    }

    private fun resetList() {
        wordList = getWords()
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            resetList()
        }
        _word.value = wordList.removeAt(0)
        restartTimer()
    }

    private fun restartTimer(){
        timer.cancel()
        timer.start()
    }

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }


    fun onGameFinishComplete(){
        _eventGameFinish.value = false
    }

    private fun getWords() =
            mutableListOf(
                    "queen",
                    "hospital",
                    "basketball",
                    "cat",
                    "change",
                    "snail",
                    "soup",
                    "calendar",
                    "sad",
                    "desk",
                    "guitar",
                    "home",
                    "railway",
                    "zebra",
                    "jelly",
                    "car",
                    "crow",
                    "trade",
                    "bag",
                    "roll",
                    "bubble"
            )
}