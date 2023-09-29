package com.code93.e_movie.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code93.e_movie.domain.usecase.TheMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val theMovieUseCase: TheMovieUseCase) :
    ViewModel() {
    fun getTopRated() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                theMovieUseCase.getTopRated()
            }
            if (result != null) {
                Log.d("LOG MOVIE", "Results ${result.totalResults}")
            }
        }
    }
}