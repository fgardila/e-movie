package com.code93.e_movie.domain.model

data class DetailsModel(
    val backdropPath: String,
    val genres: List<GenreMovieModel>,
    val id: Int,
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val status: String,
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Double
)