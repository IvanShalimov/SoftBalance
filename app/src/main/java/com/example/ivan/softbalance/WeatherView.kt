package com.example.ivan.softbalance

import com.example.ivan.softbalance.model.WeatherItem
import com.hannesdorfmann.mosby3.mvp.MvpView

interface WeatherView:MvpView {

    fun showNewData(data:ArrayList<WeatherItem>)
}