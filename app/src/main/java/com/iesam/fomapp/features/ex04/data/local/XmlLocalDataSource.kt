package com.iesam.fomapp.features.ex04.data.local

import android.content.Context
import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex04.domain.Burger

class XmlLocalDataSource (private val context : Context){

    private val sharedPref = context.getSharedPreferences("burger", Context.MODE_PRIVATE)

    fun getBurger () : Either<ErrorApp, Burger>{
        return try{
            Burger (
                sharedPref.getString("ofert", "")!!,
                sharedPref.getString("tittle", "")!!,
                sharedPref.getString("likes", "")!!,
                sharedPref.getString("time", "")!!
            ).right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }

    fun saveBurger(burger: Burger) : Either<ErrorApp, Boolean> {
        return try{
            with(sharedPref.edit()){
                putString("ofert", burger.ofert)
                putString("tittle", burger.tittle)
                putString("likes", burger.likes)
                putString("time", burger.time)
                apply()
            }
            true.right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}

