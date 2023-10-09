package com.iesam.fomapp.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp

interface UserRepository {

    fun saveUser (name : String, surname : String) : Either<ErrorApp, Boolean>

    fun getUser (userId : Int) : Either<ErrorApp, User>

    fun deleteUser (userId : Int) : Either<ErrorApp, Boolean>

}