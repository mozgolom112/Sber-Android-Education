package com.mozgolom112.fundamentalsandroid.adapters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mozgolom112.fundamentalsandroid.moviedetails.MovieDetailsFragment
import com.mozgolom112.fundamentalsandroid.models.MovieModel


class DetailsPagerAdapter(
    fragmentManager: FragmentManager,
    private val listOfMovieModels: List<MovieModel>

) : FragmentStatePagerAdapter(fragmentManager) {
    //TODO('Called every time, when we change page')
    override fun getItem(position: Int): MovieDetailsFragment {
        return MovieDetailsFragment.newInstance(listOfMovieModels[position])
    }

    override fun getCount(): Int = listOfMovieModels.size
}