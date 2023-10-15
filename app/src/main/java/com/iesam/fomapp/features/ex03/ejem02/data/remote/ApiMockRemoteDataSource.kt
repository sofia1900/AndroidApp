package com.iesam.fomapp.features.ex03.ejem02.data.remote

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem02.domain.Conversation

class ApiMockRemoteDataSource {

    fun getConversations () : Either<ErrorApp, List<Conversation>> {
        val c1= Conversation("Fin de semana", "Sofia: ", "sticker","9:49")
        val c2 = Conversation("Francisco Flores", "escribiendo...: ", "", "9:45")

        return listOf<Conversation>(c1, c2).right()
    }
}