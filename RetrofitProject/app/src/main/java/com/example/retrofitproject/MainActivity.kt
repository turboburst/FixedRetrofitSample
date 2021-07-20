package com.example.retrofitproject

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitproject.adapter.CustomAdapter
import com.example.retrofitproject.model.RetroPhoto
import com.example.retrofitproject.network.GetDataService
import com.example.retrofitproject.network.RetrofitCloientInstance
import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var adapter:CustomAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Loading")
        progressDialog.show()

        val service = RetrofitCloientInstance.getRetrofitInstance()?.create(GetDataService::class.java)

        var call = service?.getAllPhontos()
        call?.enqueue(object :Callback<List<RetroPhoto>>{
            override fun onResponse(response: Response<List<RetroPhoto>>?, retrofit: Retrofit?) {
                progressDialog.dismiss()
                generateDataList(response!!.body())
            }

            override fun onFailure(t: Throwable?) {
                progressDialog.dismiss()
                Toast.makeText(this@MainActivity, "something went wrong...please try later!", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun generateDataList(photoList: List<RetroPhoto>){
        recyclerView = findViewById(R.id.customRecyclerView)
        adapter = CustomAdapter(this, photoList)
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
        super.onDestroy()
    }
}