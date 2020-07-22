package com.mozgolom112.fundamentalsandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_INT
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_STRING
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

    }
}