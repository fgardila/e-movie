package com.code93.e_movie.data.network.response


import com.code93.e_movie.domain.model.DetailsModel
import com.code93.e_movie.domain.model.getGenresByIds
import com.google.gson.annotations.SerializedName

data class DetailsResponse(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("revenue")
    val revenue: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Double
) {
    fun toDomain(): DetailsModel {
        return DetailsModel(
            backdropPath = this.backdropPath.orEmpty(),
            genres = getGenresByIds(getIdsFromGenres(genres)),
            id = this.id,
            originalLanguage = this.originalLanguage,
            overview = this.overview,
            popularity = this.popularity,
            posterPath = this.posterPath,
            releaseDate = this.releaseDate,
            status = this.status,
            tagline = this.tagline,
            title = this.title,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount
        )
    }
}