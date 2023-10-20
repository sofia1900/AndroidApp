package com.iesam.fomapp.features.ex03.ejem02.data.remote

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem02.domain.Conversation
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRemoteDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dam.sitehub.es/curso-2023-2024/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var apiService: ApiService = retrofit.create(ApiService::class.java)

    fun getConversation () : Either<ErrorApp, List<Conversation>> {
        try {
            val response: Response<List<Conversation>> = apiService.getConversations().execute()
            if (response.isSuccessful) {
                val items = response.body()!!
                var conversations : List<Conversation> = listOf()
                for (item in items){
                    val name = item.name
                    val msg = item.msg
                    val time = item.time
                    val unreadMsg = item.unreadMsg
                    val urlPerfile = item.urlPerfile

                    conversations

                }
                val title = items.title
                val discount = items.discount
                val rate = items.rate
                val time = items.time
                val image = items.url_image

                return Burger(title, discount, rate, time, image).right()
            } else {
                throw RuntimeException()
            }
        } catch (e: Exception) {
            return ErrorApp.UnknowError.left()
        }
    }
}