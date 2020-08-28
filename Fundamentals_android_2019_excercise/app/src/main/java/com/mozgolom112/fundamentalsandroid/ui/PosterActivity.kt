package com.mozgolom112.fundamentalsandroid.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import coil.api.load
import com.mozgolom112.fundamentalsandroid.R
import kotlinx.android.synthetic.main.activity_poster.*
import java.io.File

class PosterActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)

        val posterPath = intent.getStringExtra(POSTER_PATH)
        if (posterPath == null) {
            AlertDialog.Builder(this)
                .setMessage("Error")
                .setPositiveButton("Ok") { _, _ ->
                    finishAfterTransition()
                }
                .create()
                .show()
            return
        }

        imgvPosterFile.load(File(posterPath))
    }

    companion object {

        private const val POSTER_PATH = "POSTER_PATH"

        fun newIntent(context: Context, posterPath: String): Intent {
            return Intent(context, PosterActivity::class.java)
                .putExtra(POSTER_PATH, posterPath)
        }
    }
}