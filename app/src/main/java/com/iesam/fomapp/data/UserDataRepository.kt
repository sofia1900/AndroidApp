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

    override fun getUser(userId : Int): Either<ErrorApp, User> {
        return localDataSource.getUserById(userId)
    }

    override fun deleteUser(userId : Int ): Either<ErrorApp, Boolean> {
        return localDataSource.deleteUserById(userId)
    }
}