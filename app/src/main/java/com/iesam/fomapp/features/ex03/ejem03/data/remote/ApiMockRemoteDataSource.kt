package com.iesam.fomapp.features.ex03.ejem03.data.remote

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem03.domain.Film

class ApiMockRemoteDataSource {

    fun getFilms () : Either<ErrorApp, List<Film>> {

        val film1 = Film("1","Jungle Cruise", "12", "1.03 GB", "2 h 9 min")
        val film2 = Film("2", "Black Widow", "12", "1.15 GB", "2 h 13 min")

        return listOf<Film>(film1, film2).right()

    }

}