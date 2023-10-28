package com.iesam.fomapp.features.ex03.ejem06.domain

import com.google.gson.annotations.SerializedName

data class Dog (
    val id : String,
    val name : String,
    val description : String,
    val sex : String,
    val date : String,
    val url : String
)

data class DogApiModel (
    @SerializedName("name") val name : String,
    @SerializedName("short_description") val description : String,
    @SerializedName("sex") val sex : String,
    @SerializedName("date_birth") val date : String,
    @SerializedName("url_image") val url : String,
)