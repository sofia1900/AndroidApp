package com.iesam.fomapp.features.ex03.ejem04.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp

class GetAlojamientoUseCase (private val alojamientoRepository: AlojamientoRepository) {

    operator fun invoke () : Either<ErrorApp, Alojamiento>{
        return alojamientoRepository.getAlojamiento()
    }

}