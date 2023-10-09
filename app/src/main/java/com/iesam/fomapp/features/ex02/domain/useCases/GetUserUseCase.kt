package com.iesam.fomapp.features.ex02.domain.useCases

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.features.ex02.domain.User
import com.iesam.fomapp.features.ex02.domain.UserRepository

class GetUserUseCase (private val userRepository : UserRepository) {

    operator fun invoke (idUser : Int) : Either<ErrorApp, User> {
        return userRepository.getUser(idUser)
    }
}