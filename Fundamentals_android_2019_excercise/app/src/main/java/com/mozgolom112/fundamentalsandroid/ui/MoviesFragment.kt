package com.mozgolom112.fundamentalsandroid.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.adapters.recyclerAdapters.MovieRecyclerAdapter
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private var recycleAdapter: MovieRecyclerAdapter? = null
    private val viewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMovieAdapter()
        setObservers()
    }

    private fun setObservers() {
        viewModel.apply {
            movies.observe(viewLifecycleOwner, Observer { movies ->
                recycleAdapter?.submitList(movies)
            })
        }
    }

    private fun setMovieAdapter() {

        recycleAdapter =
            MovieRecyclerAdapter { position ->
                navigateToDetailsFragment(viewModel.movies.value ?: emptyList(), position)
            }

        recyclervMoviesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recycleAdapter
        }
        addItemDecoration()
    }

    private fun navigateToDetailsFragment(movies: List<Movie>, position: Int): Unit {

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