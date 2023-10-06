package com.iesam.fomapp.presentation

import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.domain.useCases.SaveUserUseCase

class MainViewModel (val saveUserUseCase: SaveUserUseCase){

    fun saveUser (name : String, surname : String){
        saveUserUseCase.invoke(name, surname).fold(
            {responseError(it)},
            {responseSuccess(it)}
        )
    }

    private fun responseError(errorApp: ErrorApp) {
    }

    private fun responseSuccess(isOk: Boolean) {
    }


}