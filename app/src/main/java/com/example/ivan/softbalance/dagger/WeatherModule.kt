package com.example.ivan.softbalance.dagger

import com.example.ivan.softbalance.presenter.Parser
import com.example.ivan.softbalance.interactor.WeatherInteractor
import com.example.ivan.softbalance.presenter.WeatherPresenter
import dagger.Module
import dagger.Provides

@Module
class WeatherModule {

    @Provides
    fun providePresenter(parser: Parser, interactor: WeatherInteractor)
            = WeatherPresenter(parser, interactor)

    @Provides
    fun provideParser() = Parser()

    @Provides
    fun provideInteractor() = WeatherInteractor()
}