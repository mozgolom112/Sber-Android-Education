package com.mozgolom112.fundamentalsandroid.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mozgolom112.fundamentalsandroid.models.Movie

class DetailsFragmentAdapter(
    fragmentManager: FragmentManager,
    private val listOfMovies: List<Movie>
    //TODO('Когда нибудь заменить на ViewPager2')
    ) : FragmentStatePagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int = listOfMovies.size
}