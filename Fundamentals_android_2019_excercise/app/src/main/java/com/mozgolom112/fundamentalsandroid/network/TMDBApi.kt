package com.mozgolom112.fundamentalsandroid.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBInterface {
    //TODO("Remove api key")
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key: String = "0ab78bd418ad0887fbd33013f722a8a4"
    ): Deferred<NetworkMovieContainer>

    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = "0ab78bd418ad0887fbd33013f722a8a4"
    ) : Deferred<NetworkTrailerContainer>
}

//TMDB - The Movie Data Base - free api
object TMDB {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val TMDBApi: TMDBInterface = retrofit.create()
}