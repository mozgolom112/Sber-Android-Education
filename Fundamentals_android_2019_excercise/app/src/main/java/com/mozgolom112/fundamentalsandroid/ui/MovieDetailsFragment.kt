package com.mozgolom112.fundamentalsandroid.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.models.MovieModel
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_MOVIE
import com.mozgolom112.fundamentalsandroid.support.URL_TO_TRAILER
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    companion object {
        fun newInstance(movie: Movie) : MovieDetailsFragment {
            val fragment =
                MovieDetailsFragment()
            addArguments(
                movie,
                fragment
            )
            return fragment
        }

        private fun addArguments(movie: Movie, fragment: MovieDetailsFragment) {
            val args: Bundle = Bundle()
            args.putParcelable(KEY_EXTRA_MOVIE, movie)
            fragment.arguments = args
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        val movie = arguments?.get(KEY_EXTRA_MOVIE) as Movie
        movie?.apply {
            txtvFilmTitle.text = title
            txtvOverviewText.text = overview
            txtvReleaseDate.text = getString(R.string.release_date)

            //TODO("Add picture by coil")
            //imgvBackGround.setImageResource(posterImageId)
            //imgvSmallImage.setImageResource(posterImageId)
        }
    }

}