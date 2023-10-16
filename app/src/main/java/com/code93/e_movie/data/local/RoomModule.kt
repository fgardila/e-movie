package com.code93.e_movie.data.local

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.code93.e_movie.data.local.dao.TopRatedLocalDao
import com.code93.e_movie.data.local.dao.UpcomingLocalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideRoom(application: Application): MovieDatabase {
        return Room.databaseBuilder(
            application,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }

    @Provides
    fun provideTopRatedLocalDao(database: MovieDatabase): TopRatedLocalDao {
        return database.topRatedLocalDao()
    }

    @Provides
    fun provideUpcomingLocalDao(database: MovieDatabase): UpcomingLocalDao {
        return database.upcomingLocalDao()
    }

    // Define migración si es necesario (por ejemplo, de versión 1 a versión 2)
    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Agregar código de migración aquí si es necesario
        }
    }


}