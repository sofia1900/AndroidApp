package com.iesam.fomapp.domain.useCases

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.domain.UserRepository

class DeleteUserUseCase (private val userRepository: UserRepository) {

    operator fun invoke () : Either<ErrorApp, Boolean> {
        return userRepository.deleteUser()
    }
}