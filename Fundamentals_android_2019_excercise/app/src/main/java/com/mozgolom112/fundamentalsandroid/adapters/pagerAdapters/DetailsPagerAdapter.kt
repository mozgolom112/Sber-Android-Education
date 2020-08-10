package com.mozgolom112.fundamentalsandroid.adapters.pagerAdapters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mozgolom112.fundamentalsandroid.ui.MovieDetailsFragment
import com.mozgolom112.fundamentalsandroid.models.MovieModel

class DetailsPagerAdapter(
    fragmentManager: FragmentManager,
    private val listOfMovieModels: List<MovieModel>
    //TODO('Use viewPager2')
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    //TODO('Called every time, when we change page')
    override fun getItem(position: Int): MovieDetailsFragment {
        return MovieDetailsFragment.newInstance(listOfMovieModels[position])
    }

    override fun getCount(): Int = listOfMovieModels.size
}