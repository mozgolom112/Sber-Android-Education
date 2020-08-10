package com.mozgolom112.fundamentalsandroid.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.adapters.recyclerAdapters.MovieRecyclerAdapter
import com.mozgolom112.fundamentalsandroid.models.MovieModel
import com.mozgolom112.fundamentalsandroid.support.DataUtil
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private var recycleAdapter: MovieRecyclerAdapter? = null
    private val movies = DataUtil.generateMovies()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMovieAdapter()
    }

    private fun setMovieAdapter() {

        recycleAdapter =
            MovieRecyclerAdapter { position ->
                navigateToDetailsFragment(movies, position)
            }

        recyclervMoviesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recycleAdapter
        }
        recycleAdapter?.submitList(movies)
        addItemDecoration()
    }

    private fun navigateToDetailsFragment(movies: List<MovieModel>, position: Int): Unit {

        findNavController()
            .navigate(
                MoviesFragmentDirections.actionMoviesFragmentToGalleryDetailsFragment(
                    movies.toTypedArray(),
                    position
                )
            )
    }

    private fun addItemDecoration() {

        val decoration = object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.set(20, 20, 20, 20)
            }
        }
        recyclervMoviesList.addItemDecoration(decoration)
    }
}