package com.iesam.fomapp.features.ex03.ejem04.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.features.ex03.ejem04.domain.Alojamiento
import com.iesam.fomapp.features.ex03.ejem04.domain.GetAlojamientoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex03_4ViewModel (private val getAlojamientoUseCase: GetAlojamientoUseCase ): ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState : LiveData<UiState> = _uiState

    fun loadAlojamiento (){
        viewModelScope.launch(Dispatchers.IO){
            getAlojamientoUseCase().fold(
                {responseError(it)},
                {responseSucess(it)}
            )
        }
    }


    private fun responseError (errorApp: ErrorApp){}

    private fun responseSucess(alojamiento: Alojamiento){
        _uiState.postValue(UiState(alojamiento = alojamiento))
    }


    data class UiState(
        val errorApp : ErrorApp? = null,
        val isLoading : Boolean = false,
        val alojamiento: Alojamiento? = null
    )

}