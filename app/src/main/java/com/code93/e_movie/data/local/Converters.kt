package com.code93.e_movie.data.local

import androidx.room.TypeConverter
import com.code93.e_movie.data.local.model.ResultLocal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromList(resultLocal: List<ResultLocal>?): String? {
        return Gson().toJson(resultLocal)
    }

    @TypeConverter
    fun toList(resultLocalsJson: String?): List<ResultLocal>? {
        val type = object : TypeToken<List<ResultLocal>>() {}.type
        return Gson().fromJson(resultLocalsJson, type)
    }
}