package com.iesam.fomapp.features.ex03.ejem02.data

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem02.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem02.data.remote.ApiMockRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem02.domain.Conversation
import com.iesam.fomapp.features.ex03.ejem02.domain.ConversationRepository

class ConversationDataRepository (private val xmlLocalDataSource: XmlLocalDataSource,
                                  private val apiMockRemoteDataSource: ApiMockRemoteDataSource) : ConversationRepository {
    override fun findAllConversation(): Either<ErrorApp, List<Conversation>> {
        val resultConverLocal = xmlLocalDataSource.findAllConversations()
        if (resultConverLocal.isRight() && !!resultConverLocal.get().isEmpty()) return resultConverLocal.get().right()
        else{
            return apiMockRemoteDataSource.getConversations().map {
               for (c in it){
                   xmlLocalDataSource.saveConversation(c)
               }
                it
            }

        }
    }
}