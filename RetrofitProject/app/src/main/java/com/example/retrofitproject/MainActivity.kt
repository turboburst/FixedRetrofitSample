package com.example.retrofitproject

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitproject.adapter.CustomAdapter
import com.example.retrofitproject.databinding.ActivityMainBinding
import com.example.retrofitproject.model.RetroPhoto
import com.example.retrofitproject.model.RetroPhotoViewModel
import com.example.retrofitproject.network.GetDataService
import com.example.retrofitproject.network.RetrofitCloientInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var adapter:CustomAdapter
    private lateinit var recyclerView: RecyclerView
//    private lateinit var photoList: List<RetroPhoto>
    private lateinit var progressDialog: ProgressDialog
    private val model:RetroPhotoViewModel by viewModels<RetroPhotoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Hello log", "${model?.liveDataRetroPhotos.value?.size}")
        val mBinding:ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.lifecycleOwner = this
        mBinding.theRetroPhotoViewmodel = model

        /*val dataListObserver = Observer<List<RetroPhoto>>{
                newPhotos -> photoList = newPhotos
        }
        model.liveDataRetroPhotos.observe(this, dataListObserver)*/

        progressDialog = ProgressDialog(this@MainActivity)

        val service = RetrofitCloientInstance.getRetrofitInstance()?.create(GetDataService::class.java)
        var call = service?.getAllPhontos()

        if(model == null || model.liveDataRetroPhotos == null || model.liveDataRetroPhotos.value == null ||
                model.liveDataRetroPhotos.value!!.size == 0){

            progressDialog.setMessage("Loading")
            progressDialog.show()
            call?.enqueue(object :Callback<List<RetroPhoto>>{
                override fun onResponse(response: Response<List<RetroPhoto>>?, retrofit: Retrofit?) {
                    progressDialog.dismiss()
                    model.liveDataRetroPhotos.value = response!!.body()
                    generateDataList()
                }

                override fun onFailure(t: Throwable?) {
                    progressDialog.dismiss()
                    Toast.makeText(this@MainActivity, "something went wrong...please try later!", Toast.LENGTH_LONG).show()
                }
            })
        }
        else{
            generateDataList()
        }

    }

    private fun generateDataList(){
        recyclerView = findViewById(R.id.customRecyclerView)
        adapter = CustomAdapter(this, model?.liveDataRetroPhotos)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        progressDialog.dismiss()
        super.onDestroy()

    }

    override fun onRestart() {
        super.onRestart()
    }

}