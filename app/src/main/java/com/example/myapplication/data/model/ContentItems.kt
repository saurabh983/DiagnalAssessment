package com.example.myapplication.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContentItems (@SerializedName("content")
                         @Expose
                         var content: List<Content>? = null)