package com.example.ivan.softbalance.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.ivan.softbalance.R
import com.example.ivan.softbalance.model.WeatherItem

class WeatherListViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
    val date:TextView = itemView.findViewById(R.id.date)
    val temper:TextView = itemView.findViewById(R.id.temper)

    fun showWeatherItem(item:WeatherItem){
        date.text = item.date
        temper.text = item.temperature
    }
}