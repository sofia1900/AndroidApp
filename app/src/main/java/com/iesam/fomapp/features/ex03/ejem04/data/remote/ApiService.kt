package com.iesam.fomapp.features.ex03.ejem04.data.remote

import com.iesam.fomapp.features.ex03.ejem04.domain.AlojamientoApiModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("youth-card-view.json")
    fun getAlojamiento () : Call<AlojamientoApiModel>

}