package com.iesam.fomapp.features.ex02.domain.useCases

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.features.ex02.domain.UserRepository

class DeleteUserUseCase (private val userRepository: UserRepository) {

    operator fun invoke (idUser : Int) : Either<ErrorApp, Boolean> {
        return userRepository.deleteUser(idUser)
    }
}