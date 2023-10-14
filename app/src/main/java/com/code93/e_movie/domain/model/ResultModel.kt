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

fun createListReleaseYear(listResultModel: List<ResultModel>): List<String> {
    return listResultModel.map { it.releaseDate.substring(0, 4) }
}

fun createListOriginalLanguage(listResultModel: List<ResultModel>): List<String> {
    val listNew = listResultModel.map { it.originalLanguage }
    val listDistinct = listNew.distinct()
    return listDistinct
}