package com.example.ivan.softbalance.dagger

import com.example.ivan.softbalance.WeatherPresenter
import dagger.Component

@Component(modules = [(WeatherModule::class)])
interface WeatherComponent {

    fun getPresenter() :WeatherPresenter
}