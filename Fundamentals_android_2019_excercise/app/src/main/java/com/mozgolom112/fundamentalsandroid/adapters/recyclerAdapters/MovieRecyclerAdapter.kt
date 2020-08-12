package com.mozgolom112.fundamentalsandroid.adapters.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mozgolom112.fundamentalsandroid.adapters.diffCallback.MovieDiffCallback
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.viewholders.MovieViewHolder

class MovieRecyclerAdapter(private val clickListener: (Int) -> Unit) :
    ListAdapter<Movie, MovieViewHolder>(
        MovieDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(MovieViewHolder.LAYOUT, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }


}