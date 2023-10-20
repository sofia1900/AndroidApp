package com.iesam.fomapp.features.ex03.ejem06.data.remote

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem06.domain.Dog
import com.iesam.fomapp.features.ex03.ejem06.domain.DogApiModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRemoteDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dam.sitehub.es/curso-2023-2024/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var apiService: ApiService = retrofit.create(ApiService::class.java)

    fun getDog () : Either<ErrorApp, Dog> {
        try {
            val response: Response<DogApiModel> = apiService.getDog().execute()
            if (response.isSuccessful) {
                val items = response.body()!!
                val name = items.name
                val description = items.description
                val sex = items.sex
                val date = items.date
                val image = items.url

                return Dog("1", name, description, sex, date, image).right()
            } else {
                throw RuntimeException()
            }
        } catch (e: Exception) {
            return ErrorApp.UnknowError.left()
        }
    }


}