package com.code93.e_movie.data

import android.util.Log
import com.code93.e_movie.data.network.TheMovieApiService
import com.code93.e_movie.domain.Repository
import com.code93.e_movie.domain.model.DetailsModel
import com.code93.e_movie.domain.model.TopRatedModel
import com.code93.e_movie.domain.model.UpcomingModel
import com.code93.e_movie.domain.model.VideoModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val theMovieApiService: TheMovieApiService): Repository {
    override suspend fun getTopRated(): TopRatedModel? {
        runCatching { theMovieApiService.getTopRated() }
            .onSuccess {
                return it.toDomain()
            }
            .onFailure {
                Log.i("Log App", "Ha ocurrido un error ${it.message}")
            }
            return null
    }

    override suspend fun getUpcoming(): UpcomingModel? {
        runCatching { theMovieApiService.getUpcoming() }
            .onSuccess {
                return it.toDomain()
            }
            .onFailure {
                Log.i("Log App", "Ha ocurrido un error ${it.message}")
            }
        return null
    }

    override suspend fun getDetails(idMovie: Int): DetailsModel? {
        runCatching { theMovieApiService.getDetails(idMovie) }
            .onSuccess {
                return it.toDomain()
            }
            .onFailure {
                Log.i("Log App", "Ha ocurrido un error ${it.message}")
            }
        return null
    }

    override suspend fun getVideos(idMovie: Int): VideoModel? {
        runCatching { theMovieApiService.getVideos(idMovie) }
            .onSuccess {
                return it.toDomain()
            }
            .onFailure {
                Log.i("Log App", "Ha ocurrido un error ${it.message}")
            }
        return null
    }
}