package com.example.retrofitproject

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.squareup.picasso.Picasso

class MyApplication: Application() {
    companion object{
        lateinit var builder: Picasso.Builder

    }

    override fun onCreate() {
        super.onCreate()
        setupPicasso()
    }
    fun setupPicasso(){
        builder = Picasso.Builder(this)
        //by default, builder's downloader is OkHttp3Downloader, so do not write code below
//        builder.downloader(OkHttp3Downloader(context))
        builder.indicatorsEnabled(true)
        Picasso.setSingletonInstance(builder.build())
    }
}