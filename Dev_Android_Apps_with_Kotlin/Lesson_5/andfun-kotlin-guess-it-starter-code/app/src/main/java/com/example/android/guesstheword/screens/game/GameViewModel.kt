package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    val word by lazy { MutableLiveData<String>() }
    val score by lazy { MutableLiveData<Int>() }
    private lateinit var wordList: MutableList<String>

    override fun onCleared() {
        super.onCleared()
    }

    init {
        word.value = ""
        score.value = 0
        resetList()
        nextWord()
    }

    private fun resetList() {
        wordList = getWords()
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            TODO("(0) Need to fix it")
            //gameFinished()
        } else {
            word.value = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        score.value = (score.value)?.plus(1)
        nextWord()
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