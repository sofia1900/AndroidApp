package com.iesam.fomapp.features.ex01.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp


interface UserRepository {

    fun save(input : SaveUserUseCase.Input) : Either<ErrorApp, Boolean>
    fun get() : Either<ErrorApp, User>

}