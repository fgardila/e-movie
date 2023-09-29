package com.code93.e_movie.domain.model

data class TopRatedModel(
    val page: Int,
    val resultModels: List<ResultModel>,
    val totalPages: Int,
    val totalResults: Int
)