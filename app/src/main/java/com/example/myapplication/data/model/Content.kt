package com.example.myapplication.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("poster-image")
    @Expose
    var posterImage: String? = null
)