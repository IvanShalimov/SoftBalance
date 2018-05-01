package com.example.ivan.softbalance

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ivan.softbalance.model.WeatherItem

class WeatherListAdapter: RecyclerView.Adapter<WeatherListViewHolder>() {

    var list = ArrayList<WeatherItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            WeatherListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,
                    parent,false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.showWeatherItem(list[position])
    }
}