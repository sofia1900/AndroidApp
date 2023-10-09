package com.iesam.fomapp.features.ex01.data

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.features.ex01.data.local.xmlLocalDataSource
import com.iesam.fomapp.features.ex01.domain.SaveUserUseCase
import com.iesam.fomapp.features.ex01.domain.User
import com.iesam.fomapp.features.ex01.domain.UserRepository


class UserDataRepository (private val localDataSource: xmlLocalDataSource): UserRepository {
    override fun save(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean> {
        return localDataSource.saveUser(input)
    }

    override fun get(): Either<ErrorApp, User> {
        return localDataSource.getUser()
    }

}

