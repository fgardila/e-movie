package com.code93.e_movie.data.network.response


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

fun getIdsFromGenres(genres: List<Genre>): List<Int> {
    return genres.map { it.id }
}