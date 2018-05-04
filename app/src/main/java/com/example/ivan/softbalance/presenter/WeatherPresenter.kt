package com.example.ivan.softbalance.presenter

import com.example.ivan.softbalance.interactor.WeatherInteractor
import com.example.ivan.softbalance.model.WeatherItem
import com.example.ivan.softbalance.ui.WeatherView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class WeatherPresenter(private val interactor: WeatherInteractor) :
        MvpBasePresenter<WeatherView>(), Observer< ArrayList<WeatherItem>> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: ArrayList<WeatherItem>) {
        ifViewAttached {
            it.showNewData(t)
            it.showProgressDialog(false)
        }
    }

    override fun onError(e: Throwable) {

    }

    fun search(city: String) {
        ifViewAttached {
            it.showProgressDialog(true)
            interactor
                    .search(city)
                    ?.subscribe(this)
        }

    }
}