package com.iesam.fomapp.features.ex03.ejem06.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp

interface DogRepository {
    fun getDog () : Either<ErrorApp, Dog>
}