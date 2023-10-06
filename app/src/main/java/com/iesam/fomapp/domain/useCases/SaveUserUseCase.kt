package com.iesam.fomapp.domain.useCases

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.data.UserDataRepository

class SaveUserUseCase (private val userDataRepository: UserDataRepository){

     operator fun invoke (name : String, surname : String) : Either<ErrorApp, Boolean> {
        return userDataRepository.saveUser(name, surname)
    }
}