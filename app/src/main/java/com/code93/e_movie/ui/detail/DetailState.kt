package com.code93.e_movie.ui.detail

import com.code93.e_movie.domain.model.DetailsModel

sealed class DetailState {
    object Loading : DetailState()
    data class Error(val error: String) : DetailState()
    data class Success(val detailsModel: DetailsModel) : DetailState()
}