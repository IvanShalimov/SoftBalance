package com.example.ivan.softbalance

import android.util.Log
import com.example.ivan.softbalance.model.Response
import com.example.ivan.softbalance.model.WeatherItem
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class WeatherPresenter: MvpBasePresenter<WeatherView>(), Observer<Response> {
    override fun onError(e: Throwable) {
        Log.d("Test","onError = ${e.message}")
    }

    override fun onNext(t: Response)
    {
        Log.d("Test","onNext")
        ifViewAttached {
            it.showNewData(parser.parse(t))
        }
    }

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    val parser:Parser = Parser()

    val interactor:WeatherInteractor = WeatherInteractor()

    fun search(city:String){
        if(city.length>5)
            interactor
                    .search(city)
                    ?.subscribe(this)
    }
}

class Parser{

    fun parse(response:Response):ArrayList<WeatherItem>{
        val returnList:ArrayList<WeatherItem> = ArrayList()
        val iterate = response.list.listIterator()
        while (iterate.hasNext()) {
            val item  = iterate.next()
            returnList.add(WeatherItem(item.dt_txt,"${item.main.temp}"))
        }

        return returnList
    }
}