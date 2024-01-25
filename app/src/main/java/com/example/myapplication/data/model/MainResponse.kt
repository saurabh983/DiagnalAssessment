package com.example.myapplication.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("page") @Expose
    var page: Page
)