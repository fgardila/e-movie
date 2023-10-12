package com.code93.e_movie.data.network.response


import com.code93.e_movie.domain.model.CastModel
import com.code93.e_movie.domain.model.ResultVideoModel
import com.google.gson.annotations.SerializedName

data class CreditsResponse(
    @SerializedName("cast")
    val castResponse: List<CastResponse?>?,
    @SerializedName("crew")
    val crew: List<CrewResponse?>?,
    @SerializedName("id")
    val id: Int?
) {
    fun listCast(): List<CastModel>? {
        val resultModel = mutableListOf<CastModel>()
        castResponse?.apply {
            this.map {
                it?.apply {
                    resultModel.add(it.toDomain())
                }
            }
        }
        return resultModel
    }
}