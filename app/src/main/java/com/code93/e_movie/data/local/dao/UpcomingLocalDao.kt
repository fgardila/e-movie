package com.code93.e_movie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.code93.e_movie.data.local.model.TopRatedLocal
import com.code93.e_movie.data.local.model.UpcomingLocal

@Dao
interface UpcomingLocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingLocal(upcomingLocal: UpcomingLocal)

    @Query("SELECT * FROM upcoming_local LIMIT 1")
    suspend fun getFirstUpcomingLocal(): UpcomingLocal?

    @Query("DELETE FROM upcoming_local")
    suspend fun deleteAllUpcomingLocal()
}