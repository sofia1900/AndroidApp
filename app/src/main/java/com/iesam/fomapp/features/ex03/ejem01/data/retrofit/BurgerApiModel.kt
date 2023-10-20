package com.iesam.fomapp.features.ex03.ejem01.data.retrofit

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class BurgerApiModel (

    @SerializedName("title") var title : String,
    @SerializedName("discount") var  discount : String,
    @SerializedName("rate") var  rate : String,
    @SerializedName("eta") var  time : String,
    @SerializedName("url_image") var  url_image : String

)