package com.code93.e_movie.data.network.response


import com.code93.e_movie.domain.model.VideoModel
import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<ResultVideoResponse>
) {
    fun toDomain(): VideoModel {
        return VideoModel(
            id = this.id,
            results = listResultVideoToDomain(this.results)
        )
    }
}