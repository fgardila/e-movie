package com.code93.e_movie.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.code93.e_movie.data.network.response.listToDomain
import com.code93.e_movie.domain.model.TopRatedModel
import com.code93.e_movie.domain.model.UpcomingModel

@Entity(tableName = "top_rated_local")
data class TopRatedLocal(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val page: Int,
    val resultModels: List<ResultLocal>,
    val totalPages: Int,
    val totalResults: Int
) {
    fun toDomain(): TopRatedModel {
        return TopRatedModel(
            page = this.page,
            resultModels = listToDomain(this.resultModels),
            totalPages = this.totalPages,
            totalResults = this.totalResults
        )
    }
}