package com.iesam.fomapp.features.ex03.ejem02.data.remote

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem02.domain.Conversation

class ApiMockRemoteDataRepository {

    fun getConversations () : Either<ErrorApp, List<Conversation>> {
        val c1= Conversation("Fin de semana", "Sofia: ", "9:49")
        val c2 = Conversation("Francisco Flores", "escribiendo...: ", "9:45")
        val c3 = Conversation("Carolina González", "Un desayuno inolvidable: ", "9:37")

        return listOf<Conversation>(c1, c2, c3).right()
    }
}