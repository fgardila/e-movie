package com.code93.e_movie.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code93.e_movie.domain.usecase.TheMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase: TheMovieUseCase) : ViewModel() {

    private var _state = MutableStateFlow<DetailState>(DetailState.LoadingDetail)
    val state: StateFlow<DetailState> = _state

    fun getMovie(idMovie: Int) {
        _state.value = DetailState.LoadingDetail
        _state.value = DetailState.LoadingVideo
        getDetail(idMovie)
        getVideo(idMovie)
        getCast(idMovie)
    }

    private fun getCast(idMovie: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                useCase.getCast(idMovie = idMovie)
            }
            if (result != null) {
                _state.value = DetailState.SuccessCast(result)
            } else {
                _state.value = DetailState.Error("Error al obtener actores")
            }
        }
    }

    private fun getDetail(idMovie: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                useCase.getDetails(idMovie = idMovie)
            }
            if (result != null) {
                _state.value = DetailState.SuccessDetail(result)
            } else {
                _state.value = DetailState.Error("Error al obtener detalles")
            }
        }
    }

    private fun getVideo(idMovie: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                useCase.getVideo(idMovie = idMovie)
            }
            if (result != null) {
                _state.value = DetailState.SuccessVideo(result)
            } else {
                _state.value = DetailState.Error("Error al obtener detalles")
            }
        }
    }
}