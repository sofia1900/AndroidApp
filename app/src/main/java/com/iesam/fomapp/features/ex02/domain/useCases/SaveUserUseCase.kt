package com.iesam.fomapp.features.ex02.domain.useCases

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.features.ex02.domain.UserRepository

class SaveUserUseCase (private val userRepository: UserRepository){

     operator fun invoke (name : String, surname : String) : Either<ErrorApp, Boolean> {
        return userRepository.saveUser(name, surname)
    }
}