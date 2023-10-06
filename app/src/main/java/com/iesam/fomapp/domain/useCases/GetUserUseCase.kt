package com.iesam.fomapp.domain.useCases

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.domain.User
import com.iesam.fomapp.domain.UserRepository

class GetUserUseCase (private val userRepository : UserRepository) {

    operator fun invoke () : Either<ErrorApp, User> {
        return userRepository.getUser()
    }
}