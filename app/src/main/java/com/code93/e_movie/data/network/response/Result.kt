package com.code93.e_movie.data.network.response

import android.content.Context
import com.code93.e_movie.data.local.model.ResultLocal
import com.code93.e_movie.domain.model.ResultModel
import com.code93.e_movie.domain.util.ImageUtil
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    fun toDomain(): ResultModel {
        return ResultModel(
            backdropPath = this.backdropPath,
            genresList = emptyList(),
            id = id,
            originalLanguage = this.originalLanguage,
            posterPath = "https://image.tmdb.org/t/p/w780${this.posterPath}",
            releaseDate = this.releaseDate,
            title = this.title
        )
    }

    fun toLocal(): ResultLocal {
        return ResultLocal(
            backdropPath = this.backdropPath,
            genreIds = this.genreIds,
            id = id,
            originalLanguage = this.originalLanguage,
            posterPath = this.posterPath,
            releaseDate = this.releaseDate,
            title = this.title
        )
    }
}

fun listToDomain(list: List<Result>): List<ResultModel> {
    val resultModel = mutableListOf<ResultModel>()
    list.map {
        resultModel.add(it.toDomain())
    }
    return resultModel
}

fun listToLocal(list: List<Result>): List<ResultLocal> {
    val resultLocal = mutableListOf<ResultLocal>()
    list.map {
        resultLocal.add(it.toLocal())
    }
    return resultLocal
}