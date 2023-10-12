package com.code93.e_movie.data.local.model
import com.code93.e_movie.domain.model.ResultModel

data class ResultLocal(
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val posterPath: String,
    val bitmapString: String,
    val releaseDate: String,
    val title: String
) {
    fun toDomain(): ResultModel {
        return ResultModel(
            backdropPath = this.backdropPath,
            genresList = emptyList(),
            id = id,
            originalLanguage = this.originalLanguage,
            posterPath = "https://image.tmdb.org/t/p/w780${this.posterPath}",
            bitmapString = bitmapString,
            releaseDate = this.releaseDate,
            title = this.title
        )
    }
}

fun listToDomain(list: List<ResultLocal>): List<ResultModel> {
    val resultModel = mutableListOf<ResultModel>()
    list.map {
        resultModel.add(it.toDomain())
    }
    return resultModel
}