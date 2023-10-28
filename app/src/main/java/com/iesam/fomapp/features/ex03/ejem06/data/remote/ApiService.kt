package com.iesam.fomapp.features.ex03.ejem06.data.remote

import com.iesam.fomapp.features.ex03.ejem01.domain.BurgerApiModel
import com.iesam.fomapp.features.ex03.ejem06.domain.DogApiModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("huellas-view.json")
    fun getDog(): Call<DogApiModel>
}