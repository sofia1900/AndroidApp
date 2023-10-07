package com.iesam.fomapp.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp

interface UserRepository {

    fun saveUser (name : String, surname : String) : Either<ErrorApp, Boolean>

    fun getUser () : Either<ErrorApp, User>

    fun deleteUser () : Either<ErrorApp, Boolean>

}