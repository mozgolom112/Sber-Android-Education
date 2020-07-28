package com.mozgolom112.fundamentalsandroid.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.support.DataUtil
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private var recycleAdapter: MovieRecyclerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        recycleAdapter = MovieRecyclerAdapter( onClickListener )
        recyclervMoviesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recycleAdapter
        }
        recycleAdapter?.submitList(DataUtil.generateMovies())
    }

    private val onClickListener: (Int)-> Unit = { position ->
        //TODO("Add action")
        Toast.makeText(context, "Position ${position} was clicked", Toast.LENGTH_SHORT).show()
    }
}