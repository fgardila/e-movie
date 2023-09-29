package com.code93.e_movie.data.network.response


import com.code93.e_movie.domain.model.DatesModel
import com.google.gson.annotations.SerializedName

data class Dates(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String
) {
    fun toDomain(): DatesModel {
        return DatesModel(
            maximum = this.maximum,
            minimum = this.minimum
        )
    }
}