package com.code93.e_movie.data.network.response


import com.code93.e_movie.data.local.model.TopRatedLocal
import com.code93.e_movie.data.local.model.UpcomingLocal
import com.code93.e_movie.domain.model.UpcomingModel
import com.google.gson.annotations.SerializedName

data class UpcomingResponse(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    fun toDomain(): UpcomingModel {
        return UpcomingModel(
            dates = this.dates.toDomain(),
            page = this.page,
            resultModels = listToDomain(results),
            totalPages = this.totalPages,
            totalResults = this.totalResults
        )
    }

    fun toLocal(): UpcomingLocal {
        return UpcomingLocal(
            page = this.page,
            resultModels = listToLocal(this.results),
            totalPages = this.totalPages,
            totalResults = this.totalResults
        )
    }
}