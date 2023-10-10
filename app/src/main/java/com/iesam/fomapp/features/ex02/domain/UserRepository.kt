package com.iesam.fomapp.features.ex02.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp

interface UserRepository {

    fun saveUser (name : String, surname : String) : Either<ErrorApp, Int>

    fun getUser (userId : Int) : Either<ErrorApp, User>

    fun deleteUser (userId : Int) : Either<ErrorApp, Boolean>

}