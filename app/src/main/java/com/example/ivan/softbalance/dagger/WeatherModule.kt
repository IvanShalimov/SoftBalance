package com.example.ivan.softbalance.dagger

import android.content.Context
import com.example.ivan.softbalance.R
import com.example.ivan.softbalance.presenter.Parser
import com.example.ivan.softbalance.interactor.WeatherInteractor
import com.example.ivan.softbalance.presenter.WeatherPresenter
import dagger.Module
import dagger.Provides

@Module
class WeatherModule(val context: Context) {

    @Provides
    fun providePresenter( interactor: WeatherInteractor)
            = WeatherPresenter( interactor)

    @Provides
    fun provideParser() = Parser()

    @Provides
    fun provideInteractor() = WeatherInteractor(context.getString(R.string.url),
            context.getString(R.string.api_key))


}