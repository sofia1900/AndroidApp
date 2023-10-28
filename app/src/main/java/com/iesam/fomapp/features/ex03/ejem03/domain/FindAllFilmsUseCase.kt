package com.iesam.fomapp.features.ex03.ejem03.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp

class FindAllFilmsUseCase(private val filmRepository: FilmRepository) {
    operator fun invoke () : Either<ErrorApp, List<Film>> {
        return filmRepository.getFilms()
    }
}