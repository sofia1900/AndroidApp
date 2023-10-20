package com.iesam.fomapp.features.ex03.ejem01.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.features.ex03.ejem01.domain.Burger
import com.iesam.fomapp.features.ex03.ejem01.domain.GetBurgerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex03_1ViewModel (private val getBurgerUseCase: GetBurgerUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadBurger () {
        viewModelScope.launch(Dispatchers.IO) {
            getBurgerUseCase().fold(
                {responseError(it)},
                {responseSucess(it)}
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) {}

    private fun responseSucess (burger : Burger){
        _uiState.postValue(UiState(burger = burger))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val burger: Burger? = null
    )
}