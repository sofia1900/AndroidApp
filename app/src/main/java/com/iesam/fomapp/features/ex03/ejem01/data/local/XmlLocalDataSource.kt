package com.iesam.fomapp.features.ex03.ejem01.data.local

import android.content.Context
import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex01.domain.User
import com.iesam.fomapp.features.ex03.ejem01.domain.Burger

class XmlLocalDataSource (private val context : Context){

    private val sharedPref = context.getSharedPreferences("burger", Context.MODE_PRIVATE)

    fun getBurger () : Either<ErrorApp, Burger>{
        return try{
            Burger (
                sharedPref.getString("title", "")!!,
                sharedPref.getString("discount", "")!!,
                sharedPref.getString("rate", "")!!,
                sharedPref.getString("time", "")!!,
                sharedPref.getString("url_image", "")!!
            ).right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }

    fun saveBurger(burger: Burger) : Either<ErrorApp, Boolean> {
        return try{
            with(sharedPref.edit()){
                putString("tittle", burger.title)
                putString("ofert", burger.discount)
                putString("likes", burger.rate)
                putString("time", burger.time)
                putString("url_image", burger.url_image)
                apply()
            }
            true.right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}

