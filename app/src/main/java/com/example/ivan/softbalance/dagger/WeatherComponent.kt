package com.example.ivan.softbalance.dagger

import com.example.ivan.softbalance.presenter.WeatherPresenter
import dagger.Component

@Component(modules = [(WeatherModule::class)])
interface WeatherComponent {

    fun getPresenter() : WeatherPresenter
}