package com.example.ivan.softbalance

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherInteractor {

    companion object {
        const val BASE_URL="http://api.openweathermap.org/"
    }

    private var apiService:ApiInterface? = null

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiService = retrofit?.create(ApiInterface::class.java)
    }

    fun search(city:String) = apiService?.search("$city,us","json",
            "ad0a54100d47d15b572d18c42c9e7ef4")?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())

}