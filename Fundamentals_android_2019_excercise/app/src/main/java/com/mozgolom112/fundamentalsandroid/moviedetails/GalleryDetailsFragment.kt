package com.mozgolom112.fundamentalsandroid.moviedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.adapters.DetailsPagerAdapter
import com.mozgolom112.fundamentalsandroid.models.MovieModel
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_MOVIE
import com.mozgolom112.fundamentalsandroid.support.KEY_POSITION
import kotlinx.android.synthetic.main.fragment_gallery_details.*

class GalleryDetailsFragment : Fragment(R.layout.fragment_gallery_details) {

    companion object {
        fun newInstance(movies: List<MovieModel>, position: Int): GalleryDetailsFragment {
            val fragment =
                GalleryDetailsFragment()
            putArguments(
                movies,
                position,
                fragment
            )
            return fragment
        }

        private fun putArguments(
            movies: List<MovieModel>, position: Int,
            fragment: GalleryDetailsFragment
        ) {
            val args = Bundle()
            args.run {
                putParcelableArrayList(KEY_EXTRA_MOVIE, ArrayList(movies))
                putInt(KEY_POSITION, position)
            }
            fragment.arguments = args
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPagerAdapter()
    }

    private fun setPagerAdapter() {
        val pagerAdapter = DetailsPagerAdapter(
            childFragmentManager,
            arguments?.getParcelableArrayList(KEY_EXTRA_MOVIE)
                ?: throw IllegalArgumentException("Missing movie argument")
        )
        vpPager.apply {
            adapter = pagerAdapter
            currentItem = arguments?.getInt(KEY_POSITION) ?: 0
        }

    }
}