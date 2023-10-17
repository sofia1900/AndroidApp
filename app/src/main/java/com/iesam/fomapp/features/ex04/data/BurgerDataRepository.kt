package com.iesam.fomapp.features.ex04.data

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex04.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex04.data.remote.ApiMockRemoteDataSource
import com.iesam.fomapp.features.ex04.domain.Burger
import com.iesam.fomapp.features.ex04.domain.BurgerRepository

class BurgerDataRepository (private val xmlLocalDataSource: XmlLocalDataSource,
                            private val apiMockRemoteDataSource: ApiMockRemoteDataSource)
    : BurgerRepository {
    override fun getBurguer(): Either<ErrorApp, Burger> {
        //Si está en local, devuelvo lo de local.
        //Sino, voy a red, lo descargo y lo guardo en local.
        //Devuelvo lo de red

         val bugerLocal = xmlLocalDataSource.getBurger()

         return  if (bugerLocal.isRight() && bugerLocal.get().tittle != "") bugerLocal.get().right()
         else{
             return apiMockRemoteDataSource.getBurgerMock().map {
                xmlLocalDataSource.saveBurger(it)
                 it
             }
         }

    }


}