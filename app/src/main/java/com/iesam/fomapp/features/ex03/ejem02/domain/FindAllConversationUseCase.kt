package com.iesam.fomapp.features.ex03.ejem02.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.right

class FindAllConversationUseCase (private val conversationRepository : ConversationRepository) {
    operator fun invoke () : Either<ErrorApp, List<Conversation>>{
        return conversationRepository.findAllConversation()
    }
}