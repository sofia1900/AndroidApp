package com.iesam.fomapp.features.ex03.ejem02.data

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem02.data.local.XmlLocalDataRepository
import com.iesam.fomapp.features.ex03.ejem02.data.remote.ApiMockRemoteDataRepository
import com.iesam.fomapp.features.ex03.ejem02.domain.Conversation
import com.iesam.fomapp.features.ex03.ejem02.domain.ConversationRepository

class ConversationDataRepository (private val xmlLocalDataRepository: XmlLocalDataRepository,
                                    private val apiMockRemoteDataRepository: ApiMockRemoteDataRepository) : ConversationRepository {
    override fun findAllConversation(): Either<ErrorApp, List<Conversation>> {
        val resultConverLocal = xmlLocalDataRepository.findAllConversations()
        if (resultConverLocal.isRight() && !!resultConverLocal.get().isEmpty()) return resultConverLocal.get().right()
        else{
            return apiMockRemoteDataRepository.getConversations().map {
               for (c in it){
                   xmlLocalDataRepository.saveConversation(c)
               }
                it
            }

        }
    }
}