package com.code93.e_movie.domain.model


import com.google.gson.annotations.SerializedName

data class VideoModel(
    val id: Int,
    val results: List<ResultVideoModel>
)