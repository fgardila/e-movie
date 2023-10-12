package com.code93.e_movie.ui.detail

import com.code93.e_movie.domain.model.CastModel
import com.code93.e_movie.domain.model.DetailsModel
import com.code93.e_movie.domain.model.ResultVideoModel
import com.code93.e_movie.domain.model.VideoModel

sealed class DetailState {
    object LoadingDetail : DetailState()
    object LoadingVideo : DetailState()
    object LoadingCast : DetailState()
    data class Error(val error: String) : DetailState()
    data class SuccessDetail(val detailsModel: DetailsModel) : DetailState()
    data class SuccessVideo(val videoModel: VideoModel) : DetailState()
    data class SuccessCast(val listCast: List<CastModel>) : DetailState()
}