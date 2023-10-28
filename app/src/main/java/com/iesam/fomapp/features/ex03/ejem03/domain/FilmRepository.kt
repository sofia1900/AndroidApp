package com.iesam.fomapp.features.ex03.ejem03.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp

interface FilmRepository {
    fun getFilms () : Either<ErrorApp, List<Film>>
}