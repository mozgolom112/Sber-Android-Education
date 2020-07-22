package com.mozgolom112.fundamentalsandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_INT
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_STRING
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        txtvMainText.setOnClickListener {
            navigateToSecondActivity()
        }
    }

    private fun navigateToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)

        intent.putExtra(KEY_EXTRA_STRING, "text")
        intent.putExtra(KEY_EXTRA_INT, 1)

        startActivity(intent)
    }
}