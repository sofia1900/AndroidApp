package com.iesam.fomapp.features.ex01.data.local

import android.content.Context
import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex01.domain.SaveUserUseCase
import com.iesam.fomapp.features.ex01.domain.User


class xmlLocalDataSource (private val context : Context){
                                                          //nombre del fichero
    private val sharedPref = context.getSharedPreferences("users01", Context.MODE_PRIVATE)
                                                                    //Si es accesible desde otra app

    //https://developer.android.com/training/data-storage/shared-preferences?hl=es-419
    fun saveUser(input: SaveUserUseCase.Input) : Either<ErrorApp, Boolean> {
        /*val editor = sharedPref.edit()
        editor.putString("username", userName)
        editor.putString("surname", surname)
        editor.apply()
        sharedPref.edit()*/

        return try{
            with(sharedPref.edit()){
                putString("username", input.name)
                putString("surname", input.userName)
                putString("age", input.age)
                apply()
            }
            true.right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }

    fun getUser () : Either<ErrorApp, User> {
        return try{
            User (
                sharedPref.getInt("id", 0),
                sharedPref.getString("username", "")!!,
                sharedPref.getString("surname", "")!!,
                sharedPref.getString("age", "")!!
            ).right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}