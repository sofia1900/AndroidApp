package com.iesam.fomapp.features.ex03.ejem02.data.remote

import com.iesam.fomapp.features.ex03.ejem02.domain.Conversation
import com.iesam.fomapp.features.ex03.ejem02.domain.ConversationApiModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("whatsapp-view.json")
    fun getConversations() : Call<List<ConversationApiModel>>
}