package com.mozgolom112.fundamentalsandroid.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val backdrop_path: String,
    val overview: String,
    val release_date: String
): Parcelable