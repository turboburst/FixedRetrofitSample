package com.example.retrofitproject.network

import retrofit.Retrofit
import retrofit.Converter.Factory
import retrofit.GsonConverterFactory

object RetrofitCloientInstance{
    private var retrofit1: Retrofit? = null
    private val BASE_URL = "https://jsonplaceholder.typicode.com"

    fun getRetrofitInstance():Retrofit?{
        if(retrofit1 == null)
            retrofit1 = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit1
    }
}