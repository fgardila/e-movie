package com.code93.e_movie.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.code93.e_movie.domain.model.DatesModel
import com.code93.e_movie.domain.model.ResultVideoModel
import com.code93.e_movie.domain.model.UpcomingModel

@Entity(tableName = "result_video_local")
data class ResultVideoLocal(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val iso31661: String,
    val iso6391: String,
    val key: String,
    val name: String,
    val official: Boolean,
    val publishedAt: String,
    val site: String,
    val size: Int,
    val type: String
) {
    fun toDomain(): ResultVideoModel {
        return ResultVideoModel(
            id = id,
            iso31661 = iso31661,
            iso6391 = iso6391,
            key = key,
            name = name,
            official = official,
            publishedAt = publishedAt,
            site = site,
            size = size,
            type = type
        )
    }
}