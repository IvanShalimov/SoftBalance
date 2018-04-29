package com.example.ivan.softbalance

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherInteractor {

    companion object {
        const val BASE_URL="HTTP://api.openweathermap.org/"
    }

    private var apiService:ApiInterface? = null

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        apiService = retrofit?.create(ApiInterface::class.java)
    }

    fun search(city:String)= apiService?.search(city)?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())

}