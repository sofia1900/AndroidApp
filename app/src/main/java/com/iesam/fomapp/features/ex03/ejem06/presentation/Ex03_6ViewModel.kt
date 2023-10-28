package com.iesam.fomapp.features.ex03.ejem06.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.features.ex03.ejem06.domain.Dog
import com.iesam.fomapp.features.ex03.ejem06.domain.GetDogUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex03_6ViewModel (private val getDogUseCase: GetDogUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadBurger (){
        viewModelScope.launch(Dispatchers.IO) {
            getDogUseCase().fold(
                {responseError(it)},
                {responseSucess(it)}
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) {}

    private fun responseSucess (dog : Dog){
        _uiState.postValue(UiState(dog = dog))
    }


    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val dog: Dog? = null
    )
}