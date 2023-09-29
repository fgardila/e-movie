package com.code93.e_movie.ui.home

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
class HomeViewModel @Inject constructor(private val useCase: TheMovieUseCase) : ViewModel() {

    private var _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state

    fun getMovies() {
        viewModelScope.launch {
            _state.value = HomeState.Loading
            val resultTopRated = withContext(Dispatchers.IO) {
                useCase.getTopRated()
            }
            val resultUpcoming = withContext(Dispatchers.IO) {
                useCase.getUpcoming()
            }
            if (resultTopRated != null && resultUpcoming != null) {
                _state.value = HomeState.Success(resultTopRated, resultUpcoming)
            } else {
                _state.value = HomeState.Error("Error")
            }
        }
    }
}