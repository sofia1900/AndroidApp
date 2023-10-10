package com.iesam.fomapp.features.ex02.data.local

import android.content.Context
import com.google.gson.Gson
import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex02.domain.User

class XmlLocalDataSource (private val context : Context){

    private val sharedPref = context.getSharedPreferences("users3", Context.MODE_PRIVATE)

    //https://developer.android.com/training/data-storage/shared-preferences?hl=es-419

    private val gson = Gson()
    fun saveUser(name : String, surname : String) : Either<ErrorApp, Int> {
        return try{
            with(sharedPref.edit()){
                val id : Int = (1..100).random()
                val user = User (id, name, surname)
                val jsonUser = gson.toJson(user, User::class.java) //JSON
                putString(id.toString(), jsonUser) //Guardar
                apply()

                id.right()
            }
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }
    fun getUserById(userId : Int): Either<ErrorApp, User> {
        return try {
           val jsonUser = sharedPref.getString(userId.toString(), "{}")
           return gson.fromJson(jsonUser, User ::class.java).right()  //deserializar
        } catch (ex: Exception) {
            return ErrorApp.UnknowError.left()
        }
    }

    fun deleteUserById (userId : Int) : Either<ErrorApp, Boolean>{
        return try{
            sharedPref.edit().remove(userId.toString()).apply()
            true.right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }

}