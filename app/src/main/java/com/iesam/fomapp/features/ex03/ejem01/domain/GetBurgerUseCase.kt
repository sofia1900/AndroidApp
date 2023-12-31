package com.iesam.fomapp.features.ex03.ejem01.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp

class GetBurgerUseCase (private val burgerRepository: BurgerRepository) {

    operator fun invoke() : Either<ErrorApp, Burger>{
        return burgerRepository.getBurguer()
    }
}