package com.example.retrofitproject.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RetroPhotoViewModel: ViewModel() {

    val liveDataRetroPhotos: MutableLiveData<List<RetroPhoto>> by lazy {
        MutableLiveData<List<RetroPhoto>>()
    }
}