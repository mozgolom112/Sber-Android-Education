package com.mozgolom112.fundamentalsandroid.movie

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mozgolom112.fundamentalsandroid.moviedetails.GalleryDetailsFragment
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.adapters.MovieRecyclerAdapter
import com.mozgolom112.fundamentalsandroid.models.MovieModel
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_MOVIE
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private var recycleAdapter: MovieRecyclerAdapter? = null

    companion object {
        fun newInstance(movies: List<MovieModel>): MoviesFragment{
            val fragment = MoviesFragment()
            putArguments(movies, fragment)
            return fragment
        }

        private fun putArguments(movies: List<MovieModel>, fragment: MoviesFragment) {
            val args = Bundle()
            args.putParcelableArrayList(KEY_EXTRA_MOVIE, ArrayList(movies))
            fragment.arguments = args
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMovieAdapter()
    }

    private fun setMovieAdapter() {
        val movies = arguments?.getParcelableArrayList(KEY_EXTRA_MOVIE) ?: listOf<MovieModel>()

        recycleAdapter =
            MovieRecyclerAdapter {position ->
                //TODO('Прокинится ли здесь movies?')
                showDetailsFragment(movies, position)
            }

        recyclervMoviesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recycleAdapter
        }
        recycleAdapter?.submitList(movies)
        //TODO('Вопрос. Почему не могу поставить разделители из фрагмента?')
        addItemDecoration()
    }

    private fun showDetailsFragment(movies: List<MovieModel>, position: Int): Unit {

        val viewPagerFragment = GalleryDetailsFragment.newInstance(movies, position)

        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.framelContainer, viewPagerFragment)
            .commitAllowingStateLoss()
    }

    private fun addItemDecoration() {

        val decoration = object:RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.set(20, 20,20,20)
            }
        }
        recyclervMoviesList.addItemDecoration(decoration)
    }
}