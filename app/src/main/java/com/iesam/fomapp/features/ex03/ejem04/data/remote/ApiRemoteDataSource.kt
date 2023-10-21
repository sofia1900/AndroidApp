package com.iesam.fomapp.features.ex03.ejem04.data.remote

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.left
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex03.ejem04.domain.Alojamiento
import com.iesam.fomapp.features.ex03.ejem04.domain.AlojamientoApiModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
            //Executer() ejecuta el comando en algún momento en el futuro ; Planificar el trabajo para su posterior ejecución

            if(response.isSuccessful){

                //.body() que permite extraer solo la información de nuestro interés
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

    /*
     https://devexpert.io/retrofit-android-kotlin/
     FALTA QUE LA FUNCION DEVUELVE EL EITHER

     fun getAlojamiento () : Either<ErrorApp, Alojamiento>{
        //enqueue hilo secundario
         apiService.getAlojamiento().enqueue(object : Callback<AlojamientoApiModel>{

             override fun onResponse(call: Call<AlojamientoApiModel>, response: Response<AlojamientoApiModel>) {
                 // Procesar respuesta exitosa
                 try {
                     if(response.isSuccessful){
                         val items = response.body()!!
                         val title = items.title
                         val description = items.description
                         val url = items.url
                     }
                 }catch (e : Exception) {
                     ErrorApp.UnknowError
                 }
             }

             override fun onFailure(call: Call<AlojamientoApiModel>, t: Throwable) {
                 // Procesar error en la petición
                 val error = ErrorApp.UnknowError
             }
         })
     }
     */

}