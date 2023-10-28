package com.iesam.fomapp.features.ex03.ejem06.data.local

import android.content.Context
import com.google.gson.Gson
import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem06.domain.Dog
import com.iesam.viewtapasapp.app.serialization.JsonSerialization

class XmlLocalDataSource (private val context : Context, private val serialization: JsonSerialization){

    private val sharedPref = context.getSharedPreferences("dog", Context.MODE_PRIVATE)

    fun getDog () : Either<ErrorApp, Dog>{
        return try{
            serialization
                .fromJson(sharedPref.getString("1", "{}")!!, Dog::class.java)
                .right()
        }catch (ex : Exception){
            ErrorApp.UnknowError.left()
        }

    }

    fun saveDog(dog : Dog) : Either<ErrorApp, Boolean> {
        return try{
            with(sharedPref.edit()){
                val jsonConversation = serialization.toJson(dog, Dog::class.java)
                putString(dog.id, jsonConversation)
                apply()
            }
            true.right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}