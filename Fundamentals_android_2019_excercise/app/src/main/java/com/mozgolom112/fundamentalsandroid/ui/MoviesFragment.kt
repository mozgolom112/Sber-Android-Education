package com.mozgolom112.fundamentalsandroid.ui

import android.app.Application
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.adapters.recyclerAdapters.MovieRecyclerAdapter
import com.mozgolom112.fundamentalsandroid.database.DatabaseTMDB
import com.mozgolom112.fundamentalsandroid.database.DatabaseTMDB_Impl
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.repository.cache.MoviesCache
import com.mozgolom112.fundamentalsandroid.repository.cache.SharedPreferencesImpl
import com.mozgolom112.fundamentalsandroid.repository.cache.SharedPreferencesInterface
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_MOVIE
import com.mozgolom112.fundamentalsandroid.viewmodels.MovieViewModel
import com.mozgolom112.fundamentalsandroid.viewmodels.viewmodelsfactory.MovieDetailsViewModelFactory
import com.mozgolom112.fundamentalsandroid.viewmodels.viewmodelsfactory.MovieViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private var recycleAdapter: MovieRecyclerAdapter? = null

    //ViewModel
    private val viewModelFactory by lazy { MovieViewModelFactory(requireContext()) }
    private val viewModel: MovieViewModel by viewModels { viewModelFactory }

    //Paginator
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val lastVisibleItemPosition: Int
        get() = linearLayoutManager.findLastVisibleItemPosition()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMovieAdapter()
        setObservers()
    }

    private fun setObservers() {
        viewModel.apply {
            movies.observe(viewLifecycleOwner, Observer { movies ->
                recycleAdapter?.submitList(movies)
                recycleAdapter?.notifyDataSetChanged()
            })
        }
    }

    private fun setMovieAdapter() {

        recycleAdapter =
            MovieRecyclerAdapter { position ->
                navigateToDetailsFragment(viewModel.movies.value ?: emptyList(), position)
            }


        recyclervMoviesList.apply {
            linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            adapter = recycleAdapter
            setItemAnimator(DefaultItemAnimator())

        }
        setRecyclerViewScrollListener()
        addItemDecoration()
    }
    //TODO("Add pagination https://androidwave.com/pagination-in-recyclerview/)
    //https://blog.iamsuleiman.com/android-pagination-tutorial-getting-started-recyclerview/"


    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val isItemLast =
                    lastVisibleItemPosition == recycleAdapter?.itemCount?.minus(1) ?: false
                val isLoadNow = viewModel.isLoadState?.value ?: false
                if (isItemLast && !isLoadNow) {
                    viewModel.loadNextPageOfMovies()
                }
            }
        }
        recyclervMoviesList.addOnScrollListener(scrollListener)
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