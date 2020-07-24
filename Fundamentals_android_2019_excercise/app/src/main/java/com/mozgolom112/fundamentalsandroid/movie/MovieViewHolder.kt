package com.mozgolom112.fundamentalsandroid.movie

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.models.Movie

class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    private val poster: ImageView = view.findViewById(R.id.imgvPoster)
    private val title: TextView = view.findViewById(R.id.txtvMovieTitle)
    private val description: TextView = view.findViewById(R.id.txtvMovieDiscription)

    companion object {
        const val LAYOUT = R.layout.list_item_movie
    }

    fun bind(movie: Movie, clickListener:() -> Unit){
        setImage(movie.posterImageId, poster)
        title.text = movie.title
        description.text = movie.description

        view.setOnClickListener { clickListener }
    }

    private fun setImage(resourceId: Int, view: ImageView) {
        //TODO("Add download res")
        view.setImageResource(resourceId)
    }
}