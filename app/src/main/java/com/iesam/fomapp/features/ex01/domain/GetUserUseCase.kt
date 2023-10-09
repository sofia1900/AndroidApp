package com.iesam.fomapp.features.ex01.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp

class GetUserUseCase (private val repository: UserRepository){
    operator fun invoke() : Either<ErrorApp, User> {
        return repository.get()
    }
}