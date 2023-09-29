package com.code93.e_movie.data.network.response

import com.code93.e_movie.domain.model.ResultVideoModel
import com.google.gson.annotations.SerializedName

data class ResultVideoResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("official")
    val official: Boolean,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("type")
    val type: String
) {
    fun toDomain(): ResultVideoModel {
        return ResultVideoModel(
            id = this.id,
            iso31661 = this.iso31661,
            iso6391 = this.iso6391,
            key = this.key,
            name = this.name,
            official = this.official,
            publishedAt = this.publishedAt,
            site = this.site,
            size = this.size,
            type = this.type
        )
    }
}

fun listResultVideoToDomain(list: List<ResultVideoResponse>): List<ResultVideoModel> {
    val resultModel = mutableListOf<ResultVideoModel>()
    list.map {
        resultModel.add(it.toDomain())
    }
    return resultModel
}