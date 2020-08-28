package com.mozgolom112.fundamentalsandroid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mozgolom112.fundamentalsandroid.database.Dao.MovieDao
import com.mozgolom112.fundamentalsandroid.database.Dao.TrailerDao

@Database(entities = [DatabaseMovie::class, DatabaseTrailer::class], version = 1, exportSchema = false)
abstract class DatabaseTMDB : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val trailerDao: TrailerDao

    companion object {
        private lateinit var INSTANCE: DatabaseTMDB

        // TODO("Replace with ?:")
        fun getInstance(context: Context): DatabaseTMDB {
            synchronized(DatabaseTMDB::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseTMDB::class.java,
                        "TMDB"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}