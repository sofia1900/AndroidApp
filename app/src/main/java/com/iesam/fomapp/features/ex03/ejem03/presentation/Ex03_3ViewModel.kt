package com.iesam.fomapp.features.ex03.ejem03.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.features.ex03.ejem03.domain.Film
import com.iesam.fomapp.features.ex03.ejem03.domain.FindAllFilmsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex03_3ViewModel (private val findAllFilmsUseCase: FindAllFilmsUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<Ex03_3ViewModel.UiState>()
    val uiState: LiveData<Ex03_3ViewModel.UiState> = _uiState

    fun findAllFilms(){
        viewModelScope.launch(Dispatchers.IO){
            findAllFilmsUseCase().fold(
                {responseError(it)},
                {responseSucess(it)}
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) {
    }

    private fun responseSucess (listFilms: List<Film>){
        _uiState.postValue(Ex03_3ViewModel.UiState(listFilms = listFilms))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val listFilms : List<Film>? = null
    )

}