package com.iesam.fomapp.features.ex03.ejem04.data

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem04.data.remote.ApiRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem04.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem04.domain.Alojamiento
import com.iesam.fomapp.features.ex03.ejem04.domain.AlojamientoRepository

class AlojamientoDataRepository (private val xmlLocalDataSource: XmlLocalDataSource, private val apiRemoteDataSource: ApiRemoteDataSource) : AlojamientoRepository {
    override fun getAlojamiento(): Either<ErrorApp, Alojamiento> {

        val local = xmlLocalDataSource.getAlojamiento()
        if (local.isRight() && local.get().id != null) return local.get().right()
        else{
            return apiRemoteDataSource.getAlojamiento().map {
                xmlLocalDataSource.saveAlojamiento(it)
                it
            }
        }
    }
}