package com.iesam.fomapp.features.ex03.ejem04.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp

interface AlojamientoRepository {
    fun getAlojamiento () : Either<ErrorApp, Alojamiento>
}