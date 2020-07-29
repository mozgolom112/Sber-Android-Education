package com.mozgolom112.fundamentalsandroid

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mozgolom112.fundamentalsandroid.models.Movie
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_MOVIE
import com.mozgolom112.fundamentalsandroid.support.URL_TO_TRAILER
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setOnClickListeners()
        setContent()
    }

    companion object {
        fun createIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(KEY_EXTRA_MOVIE, movie)
            return intent
        }
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

        val movie = intent?.getParcelableExtra<Movie>(KEY_EXTRA_MOVIE)
        movie?.apply {
            txtvFilmTitle.text = title
            txtvOverviewText.text = description
            txtvReleaseDate.text = getString(R.string.release_date)

            imgvBackGround.setImageResource(posterImageId)
            imgvSmallImage.setImageResource(posterImageId)
        }
    }

}