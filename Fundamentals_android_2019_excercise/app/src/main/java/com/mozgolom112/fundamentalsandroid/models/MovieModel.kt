package com.mozgolom112.fundamentalsandroid.models

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val title: String,
    val posterImageId: Int,
    val description: String
): Parcelable