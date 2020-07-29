package com.mozgolom112.fundamentalsandroid.movie

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mozgolom112.fundamentalsandroid.DetailsActivity
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.adapters.MovieRecyclerAdapter
import com.mozgolom112.fundamentalsandroid.models.Movie
import com.mozgolom112.fundamentalsandroid.support.DataUtil
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private var recycleAdapter: MovieRecyclerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMovieAdapter()


    }

    private fun setMovieAdapter() {
        recycleAdapter =
            MovieRecyclerAdapter(
                onClickListener
            )
        recyclervMoviesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recycleAdapter
        }
        recycleAdapter?.submitList(DataUtil.generateMovies())
        //TODO('Вопрос. Почему не могу поставить разделители из фрагмента?')
        addItemDecoration()
    }

    private val onClickListener: (Movie)-> Unit = { movie ->

        val intent = context?.let { DetailsActivity.createIntent(it, movie ) }
        startActivity(intent)
    }

    private fun addItemDecoration() {
        val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        decoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.color.colorPrimaryDark)!!)
        recyclervMoviesList.addItemDecoration(decoration)
    }
}