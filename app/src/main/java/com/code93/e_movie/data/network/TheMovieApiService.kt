package com.code93.e_movie.data.network

import com.code93.e_movie.data.network.response.CreditsResponse
import com.code93.e_movie.data.network.response.DetailsResponse
import com.code93.e_movie.data.network.response.TopRatedResponse
import com.code93.e_movie.data.network.response.UpcomingResponse
import com.code93.e_movie.data.network.response.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApiService {

    @GET("/3/movie/top_rated")
    suspend fun getTopRated(
        @Query("language") language: String = "es-MX",
        @Query("page") page: Int = 1,
        @Query("region") region: String = "co"
    ) : TopRatedResponse

    @GET("/3/movie/upcoming")
    suspend fun getUpcoming(
        @Query("language") language: String = "es-MX",
        @Query("page") page: Int = 1,
        @Query("region") region: String = "co"
    ): UpcomingResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") idMovie: Int,
        @Query("language") language: String = "es-MX"
    ): DetailsResponse

    @GET("/3/movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") idMovie: Int,
        @Query("language") language: String = "es-MX"
    ): VideoResponse

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") idMovie: Int,
        @Query("language") language: String = "es-MX"
    ): CreditsResponse
}