package com.iesam.fomapp.data

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.data.local.XmlLocalDataSource
import com.iesam.fomapp.domain.User
import com.iesam.fomapp.domain.UserRepository

class UserDataRepository (private val localDataSource: XmlLocalDataSource): UserRepository {
    override fun saveUser(name: String, surname: String): Either<ErrorApp, Boolean>{
        return localDataSource.saveUser(name, surname)
    }

    override fun getUser(): Either<ErrorApp, User> {
        return localDataSource.getUser()
    }
}