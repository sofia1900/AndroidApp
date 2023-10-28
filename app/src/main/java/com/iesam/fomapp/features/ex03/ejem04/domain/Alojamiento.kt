package com.iesam.fomapp.features.ex03.ejem04.domain

import com.google.gson.annotations.SerializedName

data class Alojamiento (
    val id : String,
    val title : String,
    val description : String,
    val url : String
)

data class AlojamientoApiModel (
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("url_image") val url : String
)