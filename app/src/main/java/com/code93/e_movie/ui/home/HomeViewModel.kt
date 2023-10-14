package com.code93.e_movie.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code93.e_movie.domain.model.ISOLanguageCode
import com.code93.e_movie.domain.model.ISOLanguageCode.Companion.getListISOLanguageCode
import com.code93.e_movie.domain.model.ResultModel
import com.code93.e_movie.domain.model.TopRatedModel
import com.code93.e_movie.domain.model.createListOriginalLanguage
import com.code93.e_movie.domain.model.createListReleaseYear
import com.code93.e_movie.domain.usecase.TheMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Thread.State
import java.time.Year
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: TheMovieUseCase) : ViewModel() {

    private var _stateTopRated = MutableStateFlow<HomeTopRateState>(HomeTopRateState.Loading)
    val stateTopRated: StateFlow<HomeTopRateState> = _stateTopRated

    private var _stateUpcoming = MutableStateFlow<HomeUpcomingState>(HomeUpcomingState.Loading)
    val stateUpcoming: StateFlow<HomeUpcomingState> = _stateUpcoming

    private var _listTopRated = MutableStateFlow<List<ResultModel>>(emptyList())

    private var _listForYou = MutableStateFlow<List<ResultModel>>(emptyList())
    val listForYou: StateFlow<List<ResultModel>> = _listForYou

    private var _listReleaseYear = MutableStateFlow<List<String>>(emptyList())
    val listReleaseYear: StateFlow<List<String>> = _listReleaseYear

    private var _listLanguage = MutableStateFlow<List<ISOLanguageCode>>(emptyList())
    val listLanguage: StateFlow<List<ISOLanguageCode>> = _listLanguage

    private var _languageFilter = MutableStateFlow<ISOLanguageCode?>(null)
    val languageFilter: StateFlow<ISOLanguageCode?> = _languageFilter

    private var _yearFilter = MutableStateFlow<String>("")
    val yearFilter: StateFlow<String> = _yearFilter


    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            _stateTopRated.value = HomeTopRateState.Loading
            _stateUpcoming.value = HomeUpcomingState.Loading
            val resultTopRated = withContext(Dispatchers.IO) {
                useCase.getTopRated()
            }
            val resultUpcoming = withContext(Dispatchers.IO) {
                useCase.getUpcoming()
            }
            if (resultTopRated != null) {
                _stateTopRated.value = HomeTopRateState.SuccessTopRated(resultTopRated)
                _listForYou.value = resultTopRated.resultModels
                _listTopRated.value = resultTopRated.resultModels
                _listReleaseYear.value =
                    removeDuplicatesAndSortDescending(createListReleaseYear(resultTopRated.resultModels))
                _listLanguage.value =
                    getListISOLanguageCode(createListOriginalLanguage(resultTopRated.resultModels))
            } else {
                _stateTopRated.value = HomeTopRateState.Error("Error")
            }

            if (resultUpcoming != null) {
                _stateUpcoming.value = HomeUpcomingState.SuccessUpcoming(resultUpcoming)
            } else {
                _stateUpcoming.value = HomeUpcomingState.Error("Error")
            }
        }
    }

    fun setLanguageFilter(language: ISOLanguageCode) {
        _languageFilter.value = language
        filterLanguageForYou()
    }

    private fun filterLanguageForYou() {
        val filterList =
            _listTopRated.value.filter { it.originalLanguage == _languageFilter.value!!.code }
        _listForYou.value = filterList
    }

    fun setYearFilter(year: String) {
        _yearFilter.value = year
        filterYearForYou()
    }

    private fun filterYearForYou() {
        val filterList =
            _listTopRated.value.filter { it.releaseDate.substring(0, 4) == _yearFilter.value }

        _listForYou.value = filterList
    }

    private fun removeDuplicatesAndSortDescending(years: List<String>): List<String> {
        val yearsAsIntegers = years.mapNotNull { it.toIntOrNull() }
        val uniqueSortedYears = yearsAsIntegers.distinct().sortedDescending()
        return uniqueSortedYears.map { it.toString() }
    }
}