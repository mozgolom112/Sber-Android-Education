package com.mozgolom112.fundamentalsandroid.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mozgolom112.fundamentalsandroid.database.DatabaseTrailer

@Dao
interface TrailerDao {
    @Query("SELECT * FROM trailers WHERE movieId = :movieId")
    fun getTrailerByMovieId(movieId: Int): DatabaseTrailer?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(video: DatabaseTrailer)

    @Query("DELETE FROM trailers")
    fun deleteAll()
}