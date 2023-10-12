package com.code93.e_movie.domain

import com.code93.e_movie.domain.model.CastModel
import com.code93.e_movie.domain.model.DetailsModel
import com.code93.e_movie.domain.model.TopRatedModel
import com.code93.e_movie.domain.model.UpcomingModel
import com.code93.e_movie.domain.model.VideoModel

interface Repository {

    suspend fun getTopRated(): TopRatedModel?

    suspend fun getUpcoming(): UpcomingModel?

    suspend fun getDetails(idMovie: Int): DetailsModel?

    suspend fun getVideos(idMovie: Int): VideoModel?

    suspend fun getCast(idMovie: Int): List<CastModel>?
}