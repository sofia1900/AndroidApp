package com.iesam.fomapp.features.ex03.ejem03.data

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem03.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem03.data.remote.ApiMockRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem03.domain.Film
import com.iesam.fomapp.features.ex03.ejem03.domain.FilmRepository

class FilmDataRepository(private val xmlLocalDataSource: XmlLocalDataSource, private val apiMockRemoteDataSource: ApiMockRemoteDataSource) : FilmRepository {
    override fun getFilms(): Either<ErrorApp, List<Film>> {
        val localFilms = xmlLocalDataSource.findAllFilms()
        if(localFilms.isRight() && localFilms.get().isNotEmpty()) return localFilms.get().right()
        else{
            return apiMockRemoteDataSource.getFilms().map {
                for(film in it){
                    xmlLocalDataSource.saveFilm(film)
                }
                it
            }
        }
    }


}