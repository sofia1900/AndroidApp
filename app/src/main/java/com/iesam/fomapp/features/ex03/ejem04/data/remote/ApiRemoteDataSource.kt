package com.iesam.fomapp.features.ex03.ejem04.data.remote

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem04.domain.Alojamiento
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRemoteDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dam.sitehub.es/curso-2023-2024/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var apiService: ApiService = retrofit.create(ApiService::class.java)

    fun getAlojamiento () : Either<ErrorApp, Alojamiento>{
        try {
            val response = apiService.getAlojamiento().execute()
            if(response.isSuccessful){
                val items = response.body()!!
                val title = items.title
                val description = items.description
                val url = items.url

                return Alojamiento("1", title, description, url).right()
            }else{
                throw Exception()
            }
        }catch (e : Exception ){
            return ErrorApp.UnknowError.left()
        }
    }
}