package com.mozgolom112.fundamentalsandroid.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.adapters.pagerAdapters.DetailsPagerAdapter
import com.mozgolom112.fundamentalsandroid.domain.Movie
import kotlinx.android.synthetic.main.fragment_gallery_details.*

class GalleryDetailsFragment : Fragment(R.layout.fragment_gallery_details) {

    private var currentPosition: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GalleryDetailsFragmentArgs.fromBundle(
            requireArguments()
        ).apply {
            setPagerAdapter(listMovies.toList())
            currentPosition = position
        }
    }

    private fun setPagerAdapter(movies: List<Movie>) {
        val pagerAdapter =
            DetailsPagerAdapter(
                childFragmentManager,
                movies
            )
        vpPager.apply {
            adapter = pagerAdapter
            currentItem = currentPosition
        }

    }
}