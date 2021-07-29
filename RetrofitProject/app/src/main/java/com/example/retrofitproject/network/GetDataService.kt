package com.example.retrofitproject.network

import androidx.lifecycle.MutableLiveData
import com.example.retrofitproject.model.RetroPhoto
import retrofit.Call
import retrofit.http.GET

interface GetDataService {
    @GET("/photos")
    fun getAllPhontos(): Call<List<RetroPhoto>>
}