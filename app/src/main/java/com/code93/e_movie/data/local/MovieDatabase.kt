package com.code93.e_movie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.code93.e_movie.data.local.dao.TopRatedLocalDao
import com.code93.e_movie.data.local.model.TopRatedLocal

@Database(entities = [TopRatedLocal::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun topRatedLocalDao(): TopRatedLocalDao
}