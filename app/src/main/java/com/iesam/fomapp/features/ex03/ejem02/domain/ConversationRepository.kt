package com.iesam.fomapp.features.ex03.ejem02.domain

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp

interface ConversationRepository {
     fun findAllConversation () : Either<ErrorApp, List<Conversation>>

}