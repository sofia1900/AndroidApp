package com.iesam.fomapp.features.ex04.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp

interface BurgerRepository {

    fun getBurguer() : Either<ErrorApp, Burger>

}