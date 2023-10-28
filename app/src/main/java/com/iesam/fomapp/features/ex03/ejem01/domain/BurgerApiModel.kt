package com.iesam.fomapp.features.ex03.ejem01.domain

import com.google.gson.annotations.SerializedName

data class BurgerApiModel (
    @SerializedName("title") val title : String,
    @SerializedName("discount") val discount : String,
    @SerializedName("rate") val rate : String,
    @SerializedName("eta") val time : String,
    @SerializedName("url_image") val url_image : String

)