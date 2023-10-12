package com.code93.e_movie.data.network.response


import com.code93.e_movie.domain.model.CastModel
import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("cast_id")
    val castId: Int?,
    @SerializedName("character")
    val character: String?,
    @SerializedName("credit_id")
    val creditId: String?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("order")
    val order: Int?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("profile_path")
    val profilePath: String?
) {
    fun toDomain() = CastModel(
        adult = adult,
        castId = castId,
        character = character,
        creditId = creditId,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        order = order,
        originalName = originalName,
        popularity = popularity,
        profilePath = "https://image.tmdb.org/t/p/w185$profilePath"
    )
}