package com.iesam.fomapp.data.local

import android.content.Context
import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right

class XmlLocalDataSource (private val context : Context){

    private val sharedPref = context.getSharedPreferences("users", Context.MODE_PRIVATE)

    //https://developer.android.com/training/data-storage/shared-preferences?hl=es-419
    fun saveUser(name : String, surname : String) : Either<ErrorApp, Boolean> {
        return try{
            with(sharedPref.edit()){
                putInt("id", (1..100).random())
                putString("username", name)
                putString("surname", surname)
                apply()
            }
            true.right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }

}