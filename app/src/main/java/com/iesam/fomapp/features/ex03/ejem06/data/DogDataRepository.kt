package com.iesam.fomapp.features.ex03.ejem06.data

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem06.data.remote.ApiRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem06.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem06.domain.Dog
import com.iesam.fomapp.features.ex03.ejem06.domain.DogRepository


class DogDataRepository (private val xmlLocalDataSource: XmlLocalDataSource,
    private val apiRemoteDataSource: ApiRemoteDataSource) : DogRepository {
    override fun getDog(): Either<ErrorApp, Dog> {
        val dogLocal = xmlLocalDataSource.getDog()

        return  if (dogLocal.isRight() && dogLocal.get().id != null) dogLocal.get().right()
        else{
            return apiRemoteDataSource.getDog().map {
                xmlLocalDataSource.saveDog(it)
                it
            }
        }

    }
}