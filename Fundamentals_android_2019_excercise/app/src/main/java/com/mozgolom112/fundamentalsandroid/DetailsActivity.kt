package com.mozgolom112.fundamentalsandroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_INT
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_STRING
import com.mozgolom112.fundamentalsandroid.support.URL_TO_TRAILER
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setOnClickListeners()
        setContent()
    }

    private fun setOnClickListeners() {
        btnvWatchTrailer.setOnClickListener {
            openUrl(URL_TO_TRAILER)
        }
    }

    private fun openUrl(videoUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        startActivity(intent)
    }

    private fun setContent() {
        txtvFilmTitle.text = getString(R.string.film_title)
        txtvOverviewText.text = getString(R.string.overview_avengers_endgame)
        txtvReleaseDate.text = getString(R.string.release_date)

        imgvBackGround.setImageResource(R.drawable.endgame_background)
        imgvSmallImage.setImageResource(R.drawable.endgame_image)
    }

}