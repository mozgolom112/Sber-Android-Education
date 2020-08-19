package com.mozgolom112.fundamentalsandroid.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mozgolom112.fundamentalsandroid.database.DatabaseMovie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getAll(): List<DatabaseMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<DatabaseMovie>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNewPage(movies: List<DatabaseMovie>)

    @Query("DELETE FROM movies")
    fun deleteAll()
}