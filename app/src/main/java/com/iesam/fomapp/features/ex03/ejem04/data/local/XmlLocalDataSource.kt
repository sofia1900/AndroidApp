package com.iesam.fomapp.features.ex03.ejem04.data.local

import android.content.Context
import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem04.domain.Alojamiento
import com.iesam.viewtapasapp.app.serialization.JsonSerialization

class XmlLocalDataSource (private val context : Context, private val serialization: JsonSerialization) {

    private val sharedPref = context.getSharedPreferences("alojamiento", Context.MODE_PRIVATE)

    fun getAlojamiento () : Either<ErrorApp, Alojamiento> {
        return try{
            serialization
                .fromJson(sharedPref.getString("1", "{}")!!, Alojamiento::class.java)
                .right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }

    fun saveAlojamiento (alojamiento: Alojamiento) : Either<ErrorApp, Boolean>{
        return try{
            with(sharedPref.edit()){
                val jsonConversation = serialization.toJson(alojamiento, Alojamiento::class.java)
                putString(alojamiento.id, jsonConversation)
                apply()
            }
            true.right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}