package com.example.myapplication.data.network

import com.example.myapplication.data.model.MainResponse
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {

    @GET("page{no}.json")
    suspend fun callApiLogin(@Path("no") no: String): Response<MainResponse>

}