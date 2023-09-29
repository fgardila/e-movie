package com.code93.e_movie.domain.usecase

import com.code93.e_movie.domain.Repository
import javax.inject.Inject

class TheMovieUseCase @Inject constructor(private val repository: Repository) {
    suspend fun getTopRated() = repository.getTopRated()

    suspend fun getUpcoming() = repository.getUpcoming()

    suspend fun getDetails(idMovie: Int) = repository.getDetails(idMovie)

    suspend fun getVideo(idMovie: Int) = repository.getVideos(idMovie)


}