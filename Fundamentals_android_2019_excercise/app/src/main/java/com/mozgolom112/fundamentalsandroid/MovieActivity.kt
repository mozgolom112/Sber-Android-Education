package com.mozgolom112.fundamentalsandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mozgolom112.fundamentalsandroid.movie.MoviesFragment
import com.mozgolom112.fundamentalsandroid.support.DataUtil

class MovieActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }
}