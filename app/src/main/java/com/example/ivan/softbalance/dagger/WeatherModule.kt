package com.example.ivan.softbalance.dagger

import com.example.ivan.softbalance.Parser
import com.example.ivan.softbalance.WeatherInteractor
import com.example.ivan.softbalance.WeatherPresenter
import dagger.Module
import dagger.Provides

@Module
class WeatherModule {

    @Provides
    fun providePresenter(parser:Parser,interactor: WeatherInteractor)
            = WeatherPresenter(parser,interactor)

    @Provides
    fun provideParser() = Parser()

    @Provides
    fun provideInteractor() = WeatherInteractor()
}