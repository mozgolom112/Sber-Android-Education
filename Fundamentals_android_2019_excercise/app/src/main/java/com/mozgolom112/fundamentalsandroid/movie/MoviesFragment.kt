package com.mozgolom112.fundamentalsandroid.movie

import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.adapters.MovieRecyclerAdapter
import com.mozgolom112.fundamentalsandroid.models.MovieModel
import com.mozgolom112.fundamentalsandroid.support.DataUtil
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private var recycleAdapter: MovieRecyclerAdapter? = null
    private val movies = DataUtil.generateMovies()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setMovieAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item, requireView().findNavController()) || super.onOptionsItemSelected(item)

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
        //TODO('Вопрос. Почему не могу поставить разделители из фрагмента?')
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