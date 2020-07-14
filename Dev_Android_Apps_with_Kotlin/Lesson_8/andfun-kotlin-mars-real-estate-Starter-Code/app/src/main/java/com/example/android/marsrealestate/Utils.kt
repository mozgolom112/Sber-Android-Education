package com.example.android.marsrealestate

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.marsrealestate.network.MarsProperty
import com.example.android.marsrealestate.overview.adapters.PhotoGridAdapter

fun ImageView.bindImage (imgUrl: String?){
    imgUrl?.let {
        val imgUri= it.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
                .load(imgUri)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(this)
    }
}

fun RecyclerView.bindRecyclerView(data: List<MarsProperty>?){
    val adapter = adapter as PhotoGridAdapter
    adapter.submitList(data)
}
