package com.code93.e_movie.ui.home

import com.code93.e_movie.domain.model.TopRatedModel
import com.code93.e_movie.domain.model.UpcomingModel

sealed class HomeTopRateState {
    object Loading : HomeTopRateState()

    data class SuccessTopRated(
        val topRatedModel: TopRatedModel
    ) : HomeTopRateState()

    data class Error(val error: String) : HomeTopRateState()
}

sealed class HomeUpcomingState {
    object Loading : HomeUpcomingState()

    data class SuccessUpcoming(
        val upcomingModel: UpcomingModel
    ) : HomeUpcomingState()

    data class Error(val error: String) : HomeUpcomingState()
}