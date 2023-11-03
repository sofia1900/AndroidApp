package com.iesam.fomapp.features.ex03.ejem02.data.local

import android.content.Context
import com.google.gson.Gson
import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem02.domain.Conversation

class XmlLocalDataSource (private val context : Context){

    private val sharedPref = context.getSharedPreferences("conversations", Context.MODE_PRIVATE)
    private val gson = Gson()
    fun findAllConversations () : Either<ErrorApp, List<Conversation>> {
        val conversationsMap = sharedPref.all as Map<String, String>
        val listConverGson = conversationsMap.values.map {
            gson.fromJson(it, Conversation::class.java)
        }
        return listConverGson.right()
    }

    fun saveConversation (conversation: Conversation) : Either<ErrorApp, Boolean>{
        return try{
            with(sharedPref.edit()){
                val jsonConversation = gson.toJson(conversation, Conversation::class.java)
                putString(conversation.id, jsonConversation)
                apply()
            }
            true.right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }

}