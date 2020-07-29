package com.mozgolom112.fundamentalsandroid.movie

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.models.MovieModel

class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    private val poster: ImageView = view.findViewById(R.id.imgvPoster)
    private val title: TextView = view.findViewById(R.id.txtvMovieTitle)
    private val description: TextView = view.findViewById(R.id.txtvMovieDiscription)

    companion object {
        const val LAYOUT = R.layout.list_item_movie
    }

    fun bind(movieModel: MovieModel, clickListener:(position: Int) -> Unit){
        setImage(movieModel.posterImageId, poster)
        title.text = movieModel.title
        description.text = movieModel.description

        //TODO('Вопрос, нужно ли выносить setOnClickListener в init метод?')
        view.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                clickListener(position)
            }
        }
    }

    private fun setImage(resourceId: Int, view: ImageView) {
        //TODO("Add download res")
        view.setImageResource(resourceId)
    }
}