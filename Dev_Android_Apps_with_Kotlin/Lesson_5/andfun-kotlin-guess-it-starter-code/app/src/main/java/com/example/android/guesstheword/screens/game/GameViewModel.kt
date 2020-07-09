package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)

class GameViewModel : ViewModel() {

    enum class BuzzType(val pattern: LongArray) {
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }

    companion object {
        private const val DONE = 0L
        private const val ONE_SECOND = 1000L
        //TODO("Change 10000 to 60000 after testing")
        private const val COUNTDOWN_TIME = 10000L
        private const val COUNTDOWN_PANIC_SECONDS = 3000L
    }

    private val timer: CountDownTimer by lazy {
        initCountDownTimer()
    }

    private fun initCountDownTimer() = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
        override fun onTick(millisUntilFinished: Long) {
            Log.i("Check Timer", DateUtils.formatElapsedTime(millisUntilFinished / ONE_SECOND))
            _currentTime.value = (millisUntilFinished / ONE_SECOND)
            if (millisUntilFinished / ONE_SECOND <= COUNTDOWN_PANIC_SECONDS) {
                _eventBuzz.value = BuzzType.COUNTDOWN_PANIC
            }
        }

        override fun onFinish() {
            _currentTime.value = DONE
            _eventGameFinish.value = true
            //timer.cancel()
            _eventBuzz.value = BuzzType.GAME_OVER
            Log.i("Check Timer", "Game finish")

        }
    }

    private val _currentTime = MutableLiveData<Long>()
    private val currentTime : LiveData<Long>
        get() = _currentTime
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventGameFinish  = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private val _eventBuzz = MutableLiveData<BuzzType>()
    val eventBuzz: LiveData<BuzzType>
        get() = _eventBuzz

    private lateinit var wordList: MutableList<String>

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    init {
        startInitialization()
    }

    private fun startInitialization() {
        _eventGameFinish.value = false
        _currentTime.value = 0
        _word.value = ""
        _score.value = 0
        resetList()
        nextWord()
    }

    private fun resetList() {
        wordList = getWords()
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) resetList()

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
        _eventBuzz.value = BuzzType.CORRECT
        nextWord()
    }


    fun onGameFinishComplete(){
        _eventGameFinish.value = false
    }

    fun onBuzzComplete() {
        _eventBuzz.value = BuzzType.NO_BUZZ
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