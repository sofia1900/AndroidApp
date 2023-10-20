package com.iesam.fomapp.features.ex03.ejem01.data.retrofit

import retrofit2.Call
import retrofit2.http.GET


interface BurgerApiService {

    @GET("burguer-view.json") //Cuando se invoca al método, Retrofit agrega a burger el URL
     fun getBurger(): Call<BurgerApiModel>

}

