package com.mozgolom112.fundamentalsandroid.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.api.load
import coil.transform.CircleCropTransformation
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_MOVIE
import com.mozgolom112.fundamentalsandroid.support.URL_TO_TRAILER
import com.mozgolom112.fundamentalsandroid.viewmodels.MovieDetailsViewModel
import com.mozgolom112.fundamentalsandroid.viewmodels.viewmodelsfactory.MovieDetailsViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private val viewModelFactory: MovieDetailsViewModelFactory by lazy {
        val movie = arguments?.get(KEY_EXTRA_MOVIE) as Movie
        MovieDetailsViewModelFactory(movie, requireContext())
    }

    private val viewModel: MovieDetailsViewModel by viewModels { viewModelFactory }

    companion object {
        fun newInstance(movie: Movie): MovieDetailsFragment {
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
        setObservers()
        setOnClickListeners()
        setContent()
    }

    private fun setObservers() {
        viewModel.apply {
            isTrailerFound.observe(viewLifecycleOwner, Observer {isTrailerFound ->
                if (isTrailerFound){
                    //TODO("Add loader")
                    btnvWatchTrailer.visibility = View.VISIBLE
                    btnvWatchTrailer.isClickable = true
                } else {
                    btnvWatchTrailer.visibility = View.INVISIBLE
                    btnvWatchTrailer.isClickable = false
                }
            })
        }
    }

    private fun setOnClickListeners() {
        btnvWatchTrailer.setOnClickListener {
            val trailerURL = viewModel.getTrailerUrl()
            if (trailerURL != null)
                openUrl(trailerURL)
            else {
                //TODO("Hide button")
                Toast.makeText(requireContext(), "Sorry. No trailer here", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun openUrl(videoUrl: String) {
        Log.i("Youtube intent", "${videoUrl}")
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        startActivity(intent)
    }

    private fun setContent() {

        val movie = viewModel.movie
        movie?.apply {
            txtvFilmTitle.text = title
            txtvOverviewText.text = overview
            txtvReleaseDate.text = release_date

            //TODO("Refactor code below")
            imgvBackGround.load(backdrop_path) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
            }
            imgvSmallImage.load(poster_path) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
            }
        }
    }


}