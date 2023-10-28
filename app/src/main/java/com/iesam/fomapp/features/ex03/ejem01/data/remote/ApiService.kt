package com.iesam.fomapp.features.ex03.ejem01.data.remote

import com.iesam.fomapp.features.ex03.ejem01.domain.BurgerApiModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("burguer-view.json")
    fun getBurger(): Call<BurgerApiModel>

}