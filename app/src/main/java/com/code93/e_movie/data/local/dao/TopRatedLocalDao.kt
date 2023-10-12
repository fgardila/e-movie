package com.code93.e_movie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.code93.e_movie.data.local.model.TopRatedLocal

@Dao
interface TopRatedLocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedLocal(topRatedLocal: TopRatedLocal)

    @Query("SELECT * FROM top_rated_local LIMIT 1")
    suspend fun getFirstTopRatedLocal(): TopRatedLocal?

    @Query("DELETE FROM top_rated_local")
    suspend fun deleteAllTopRatedLocal()
}