package com.code93.e_movie.ui.home

import com.code93.e_movie.domain.model.TopRatedModel
import com.code93.e_movie.domain.model.UpcomingModel

sealed class HomeState {
    object Loading : HomeState()

    data class SuccessTopRated(
        val topRatedModel: TopRatedModel
    ) : HomeState()

    data class SuccessUpcoming(
        val upcomingModel: UpcomingModel
    ) : HomeState()

    data class Error(val error: String) : HomeState()
}