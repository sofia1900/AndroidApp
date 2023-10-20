package com.iesam.fomapp.features.ex03.ejem01.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BugerApi {

    fun getBurger(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val service = retrofit.create(BurgerApiService::class.java)
        val response = service.getBurger()

        if (response.isSuccessful){

        }

    }




}
