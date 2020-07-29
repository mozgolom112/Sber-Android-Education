package com.mozgolom112.fundamentalsandroid.adapters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mozgolom112.fundamentalsandroid.moviedetails.MovieDetailsFragment
import com.mozgolom112.fundamentalsandroid.models.MovieModel

class DetailsPagerAdapter(
    fragmentManager: FragmentManager,
    private val listOfMovieModels: List<MovieModel>
    //TODO('Когда нибудь заменить на ViewPager2')
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): MovieDetailsFragment = MovieDetailsFragment.newInstance(listOfMovieModels[position])

    override fun getCount(): Int = listOfMovieModels.size
}