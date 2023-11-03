package com.iesam.fomapp.features.ex03.ejem03.data.local

import android.content.Context
import com.google.gson.Gson
import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem03.domain.Film

class XmlLocalDataSource (private val context : Context) {

    private val sharedPref = context.getSharedPreferences("films", Context.MODE_PRIVATE)
    private val gson = Gson()
    fun findAllFilms () : Either<ErrorApp, List<Film>> {
        val filmsMap = sharedPref.all as Map<String, String>
        val listGson = filmsMap.values.map {
            gson.fromJson(it, Film::class.java)
        }
        return listGson.right()
    }

    fun saveFilm (film: Film) : Either<ErrorApp, Boolean> {
        return try{
            with(sharedPref.edit()){
                val jsonConversation = gson.toJson(film, Film::class.java)
                putString(film.id, jsonConversation)
                apply()
            }
            true.right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }
}