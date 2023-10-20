package com.iesam.fomapp.features.ex03.ejem02.domain

import com.google.gson.annotations.SerializedName

data class ConversationApiModel (
    @SerializedName("title") val name : String,
    @SerializedName("subtitle") val msg : String,
    @SerializedName("time") val time : String,
    @SerializedName("unread_messages") val unreadMsg : String,
    @SerializedName("url_image") val urlPerfile : String
)