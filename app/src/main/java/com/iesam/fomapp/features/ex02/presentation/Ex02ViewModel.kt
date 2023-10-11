package com.iesam.fomapp.features.ex02.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.features.ex02.domain.User
import com.iesam.fomapp.features.ex02.domain.useCases.DeleteUserUseCase
import com.iesam.fomapp.features.ex02.domain.useCases.FindAllUsersUseCase
import com.iesam.fomapp.features.ex02.domain.useCases.GetUserUseCase
import com.iesam.fomapp.features.ex02.domain.useCases.SaveUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex02ViewModel (private val saveUserUseCase: SaveUserUseCase,
                     private val getUserUseCase: GetUserUseCase,
                     private val deleteUserUseCase: DeleteUserUseCase,
                     private val findAllUsersUseCase: FindAllUsersUseCase
) :  ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun saveUser (name : String, surname : String){
        saveUserUseCase(name, surname).fold(
            { return responseError(it) },
            { return responseSuccess(it) }
        )
    }

    fun getUser (idUser : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getUserUseCase(idUser).fold(
                {responseError(it) },
                { responseGetUserSuccess(it) }
            )
        }
    }

    fun getUsers (){
        viewModelScope.launch(Dispatchers.IO) {
            findAllUsersUseCase().fold(
                {responseError(it) },
                { responseFindAllUsersSuccess(it) }
            )
        }
    }

    fun deleteUser (idUser : Int) {
        deleteUserUseCase(idUser).fold(
            {responseError(it)},
            {responseSuccess(it)}
        )
    }

    private fun responseError(errorApp: ErrorApp) {
    }

    private fun responseSuccess(ok : Boolean) {
    }

    private fun responseGetUserSuccess (user : User){
        _uiState.postValue(UiState(user = user))
    }

    private fun responseFindAllUsersSuccess (allUsers : List<User>){
        _uiState.postValue(UiState(listUsers = allUsers))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val user: User? = null,
        val listUsers : List<User>? = null
    )

}