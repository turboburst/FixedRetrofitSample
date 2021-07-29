package com.example.retrofitproject.adapter

import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitproject.MyApplication
import com.example.retrofitproject.MyApplication.Companion.builder
import com.example.retrofitproject.R
import com.example.retrofitproject.model.RetroPhoto
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class CustomAdapter(var context:Context, var dataList: MutableLiveData<List<RetroPhoto>>): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        var textTitle: TextView
        var coverImage: ImageView
        init {
            textTitle = itemView.findViewById(R.id.title)
            coverImage = itemView.findViewById(R.id.coverImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        //var layoutInflater = LayoutInflater.from(parent.context)
        var layoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = layoutInflater.inflate(R.layout.custom_row, null)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.textTitle.text = dataList.value?.get(position)?.title

        Picasso.get().load(dataList.value?.get(position)?.thumbnailUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.coverImage)

        /*builder.build().load(dataList.get(position).thumbnailUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.coverImage)*/
    }

    override fun getItemCount(): Int {
        return dataList.value?.size?:0
    }

}