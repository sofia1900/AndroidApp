package com.iesam.fomapp.features.ex04.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.features.ex04.domain.Burger
import com.iesam.fomapp.features.ex04.domain.GetBurgerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex04ViewModel (private val getBurgerUseCase: GetBurgerUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<Ex04ViewModel.UiState>()
    val uiState : LiveData<Ex04ViewModel.UiState> = _uiState

    fun loadBurger () {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getBurgerUseCase().fold(
                {responseError(it)},
                {responseSucess(it)}
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) {
        _uiState.postValue(UiState(errorApp = errorApp, isLoading = false))
    }

    private fun responseSucess (burger : Burger){
        _uiState.postValue(UiState(burger = burger))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val burger: Burger? = null
    )
}