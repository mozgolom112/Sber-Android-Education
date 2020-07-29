package com.mozgolom112.fundamentalsandroid.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mozgolom112.fundamentalsandroid.MovieDetailsFragment
import com.mozgolom112.fundamentalsandroid.models.Movie

class DetailsPagerAdapter(
    fragmentManager: FragmentManager,
    private val listOfMovies: List<Movie>
    //TODO('Когда нибудь заменить на ViewPager2')
    ) : FragmentStatePagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment = MovieDetailsFragment()

    override fun getCount(): Int = listOfMovies.size
}