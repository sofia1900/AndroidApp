package com.iesam.fomapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.domain.User
import com.iesam.fomapp.domain.useCases.GetUserUseCase
import com.iesam.fomapp.domain.useCases.SaveUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (val saveUserUseCase: SaveUserUseCase,
                     val getUserUseCase: GetUserUseCase) :  ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun saveUser (name : String, surname : String){
        saveUserUseCase.invoke(name, surname).fold(
            {responseError(it)},
            {responseSuccess(it)}
        )
    }

    fun getUser (){
        getUserUseCase.invoke().fold(
            {responseError(it)},
            {responseGetUserSuccess(it)}
        )
    }

    fun loadUser(){
        viewModelScope.launch(Dispatchers.IO) {
            getUserUseCase().fold(
                { responseError(it) },
                { responseGetUserSuccess(it) }
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) {
    }

    private fun responseSuccess(isOk: Boolean) {
    }

    private fun responseGetUserSuccess (user : User){
        _uiState.postValue(UiState(user = user))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val user: User? = null
    )

}