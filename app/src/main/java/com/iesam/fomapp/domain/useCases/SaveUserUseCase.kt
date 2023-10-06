package com.iesam.fomapp.domain.useCases

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.domain.UserRepository

class SaveUserUseCase (private val userRepository: UserRepository){

     operator fun invoke (name : String, surname : String) : Either<ErrorApp, Boolean> {
        return userRepository.saveUser(name, surname)
    }
}