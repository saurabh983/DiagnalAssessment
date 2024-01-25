package com.example.myapplication.data.repository

import android.content.Context
import com.example.myapplication.MyApplication
import com.example.myapplication.base.BaseRepository
import com.example.myapplication.data.model.MainResponse
import com.example.myapplication.data.network.ApiInterface
import com.example.myapplication.data.network.JsonLoader
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {

    suspend fun callApiLogin(pageNum: String) =
        safeApiCall {
            val context: Context = MyApplication.getInstance().applicationContext
            val jsonLoader = JsonLoader(context) // 'this' is your context
            val json = jsonLoader.loadJsonFromAsset("page${pageNum}.json")

            val mainRespponse = Gson().fromJson(json,MainResponse::class.java)
            Response.success(mainRespponse)
        }
}