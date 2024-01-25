package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.network.ApiInterface
import com.example.myapplication.data.network.CustomLoggingInterceptor
import com.example.myapplication.data.network.ServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG)
            httpClient.addInterceptor(CustomLoggingInterceptor(context))
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideServiceGenerator(client: OkHttpClient): ApiInterface {
        return ServiceGenerator.getRetroFit(client)
    }


}