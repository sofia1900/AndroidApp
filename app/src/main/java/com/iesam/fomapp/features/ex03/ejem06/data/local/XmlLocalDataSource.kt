package com.iesam.fomapp.features.ex03.ejem06.data.local

import android.content.Context
import com.google.gson.Gson
import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem06.domain.Dog

class XmlLocalDataSource (private val context : Context){

    private val sharedPref = context.getSharedPreferences("dog", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getDog () : Either<ErrorApp, Dog>{
        val dog =  Dog(
            sharedPref.getString("id", "")!!,
            sharedPref.getString("name", "")!!,
            sharedPref.getString("description", "")!!,
            sharedPref.getString("sex", "")!!,
            sharedPref.getString("date", "")!!,
            sharedPref.getString("url", "")!!
        )

        gson.fromJson(dog.toString(), Dog::class.java)
        return dog.right()
    }

    fun saveDog(dog : Dog) : Either<ErrorApp, Boolean> {
        return try{
            with(sharedPref.edit()){
                val jsonConversation = gson.toJson(dog, Dog::class.java)
                putString(dog.id, jsonConversation)
                apply()
            }
            true.right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}