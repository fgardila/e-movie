package com.code93.e_movie.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.code93.e_movie.domain.model.DatesModel
import com.code93.e_movie.domain.model.UpcomingModel

@Entity(tableName = "upcoming_local")
data class UpcomingLocal(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val page: Int,
    val resultModels: List<ResultLocal>,
    val totalPages: Int,
    val totalResults: Int
) {
    fun toDomain(): UpcomingModel {
        return UpcomingModel(
            dates = DatesModel("", ""),
            page = this.page,
            resultModels = listToDomain(this.resultModels),
            totalPages = this.totalPages,
            totalResults = this.totalResults
        )
    }
}