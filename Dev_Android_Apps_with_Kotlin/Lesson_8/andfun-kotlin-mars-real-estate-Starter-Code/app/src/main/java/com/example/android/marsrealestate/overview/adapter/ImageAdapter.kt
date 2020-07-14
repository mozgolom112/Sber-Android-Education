package com.example.android.marsrealestate.overview.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.marsrealestate.R

class ImageAdapter {
    //TODO("Make with object")
    fun bindImage(imgView: ImageView, imgUrl: String?){
        imgUrl?.let {
            val imgUri= it.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                    .load(imgUri)
                    .into(imgView)
                    .apply{RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)}
        }
    }
}