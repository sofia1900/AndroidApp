package com.iesam.fomapp.features.ex03.ejem06.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp

class GetDogUseCase (private val dogRepository: DogRepository) {
    operator fun invoke () : Either<ErrorApp, Dog> {
        return dogRepository.getDog()
    }
}