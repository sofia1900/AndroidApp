package com.iesam.fomapp.features.ex03.ejem01.data.local

import android.content.Context
import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem01.domain.Burger
import com.iesam.viewtapasapp.app.serialization.JsonSerialization

class XmlLocalDataSource (private val context : Context, private val serialization: JsonSerialization){

    private val sharedPref = context.getSharedPreferences("burger", Context.MODE_PRIVATE)

    fun getBurger () : Either<ErrorApp, Burger>{
        return try{
            serialization
                .fromJson(sharedPref.getString("1", "{}")!!, Burger::class.java)
                .right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }

    fun saveBurger(burger: Burger) : Either<ErrorApp, Boolean> {
        return try{
            with(sharedPref.edit()){
                val jsonConversation = serialization.toJson(burger, Burger::class.java)
                putString(burger.id, jsonConversation)
                apply()
            }
            true.right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}

