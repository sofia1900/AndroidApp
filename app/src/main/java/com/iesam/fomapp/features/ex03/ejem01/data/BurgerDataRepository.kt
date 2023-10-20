package com.iesam.fomapp.features.ex03.ejem01.data

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem01.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem01.data.remote.ApiMockRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem01.data.remote.ApiRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem01.domain.Burger
import com.iesam.fomapp.features.ex03.ejem01.domain.BurgerRepository

class BurgerDataRepository (private val xmlLocalDataSource: XmlLocalDataSource,
                            private val apiRemoteDataSource: ApiRemoteDataSource)
    : BurgerRepository {
    override fun getBurguer(): Either<ErrorApp, Burger> {
        //Si está en local, devuelvo lo de local.
        //Sino, voy a red, lo descargo y lo guardo en local.
        //Devuelvo lo de red

         val bugerLocal = xmlLocalDataSource.getBurger()

         return  if (bugerLocal.isRight() && bugerLocal.get().title != "") bugerLocal.get().right()
         else{
             return apiRemoteDataSource.getBurger().map {
                xmlLocalDataSource.saveBurger(it)
                 it
             }
         }

    }


}