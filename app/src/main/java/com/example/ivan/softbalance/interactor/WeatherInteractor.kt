package com.example.ivan.softbalance.interactor

import com.example.ivan.softbalance.network.ApiInterface
import com.example.ivan.softbalance.presenter.Parser
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherInteractor(private val url: String, private val apiKey: String) {

    companion object {
        const val XML = "json"
    }

    private var apiService: ApiInterface? = null

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiService = retrofit?.create(ApiInterface::class.java)
    }

    fun search(city: String) = apiService
            ?.search("$city,us", XML, apiKey)
            ?.concatMap {
                Observable.just(Parser().parse(it))
            }
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())

}