package com.mozgolom112.fundamentalsandroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_INT
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_STRING
import com.mozgolom112.fundamentalsandroid.support.URL_TO_GITHUB
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        getExtras()
        setOnClickListeners()
    }

    private fun getExtras() {
        val extraInt = intent.getIntExtra(KEY_EXTRA_INT, 0)
        val extraString = intent.getStringExtra(KEY_EXTRA_STRING)
    }

    private fun setOnClickListeners() {
        txtvNavigateToGitHub.setOnClickListener {
            openUrl(URL_TO_GITHUB)
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}