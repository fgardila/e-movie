package com.code93.e_movie.data.network.response

import com.google.gson.annotations.SerializedName
import com.code93.e_movie.domain.model.TopRatedModel

data class TopRatedResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    fun toDomain(): TopRatedModel {
        return TopRatedModel(
            page = this.page,
            resultModels = listToDomain(this.results),
            totalPages = this.totalPages,
            totalResults = this.totalResults
        )
    }
}