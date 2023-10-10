package com.iesam.fomapp.features.ex02.domain.useCases

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.features.ex02.domain.UserRepository

class FindAllUsersUseCase (private val userRepository: UserRepository) {

    operator fun invoke () : Either<ErrorApp , Map<String, String>>{
        return userRepository.findAll()
    }
}