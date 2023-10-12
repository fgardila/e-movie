package com.code93.e_movie.domain.model

data class ResultModel(
    val backdropPath: String?,
    val genresList: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val posterPath: String,
    val bitmapString: String = "",
    val releaseDate: String,
    val title: String,
)