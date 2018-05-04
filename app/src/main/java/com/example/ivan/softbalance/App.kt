package com.example.ivan.softbalance

import android.app.Application
import com.example.ivan.softbalance.dagger.DaggerWeatherComponent
import com.example.ivan.softbalance.dagger.WeatherComponent

class App: Application() {

    val component: WeatherComponent = DaggerWeatherComponent.create()
}