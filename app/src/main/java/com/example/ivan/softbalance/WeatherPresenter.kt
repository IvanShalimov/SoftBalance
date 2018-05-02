package com.example.ivan.softbalance

import android.util.Log
import com.example.ivan.softbalance.model.Response
import com.example.ivan.softbalance.model.WeatherItem
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class WeatherPresenter(private val parser:Parser, private val interactor:WeatherInteractor): MvpBasePresenter<WeatherView>(), Observer<Response> {

    override fun onError(e: Throwable) {
        Log.d("Test","onError = ${e.message}")
    }

    override fun onNext(t: Response)
    {
        ifViewAttached {
            //parse возможно надо вынести в интерактор?
            it.showNewData(parser.parse(t))
            it.showProgressDialog(false)
        }
    }

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    fun search(city:String){
        ifViewAttached {
            it.showProgressDialog(true)
            //Вынести проверку тоже в интерактор?
            if(city.length>3)
                interactor
                        .search(city)
                        ?.subscribe(this)
        }

    }
}

class Parser{

    fun parse(response:Response):ArrayList<WeatherItem>{
        val returnList:ArrayList<WeatherItem> = ArrayList()
        val iterate = response.list.listIterator()
        while (iterate.hasNext()) {
            val item  = iterate.next()
            returnList.add(WeatherItem(item.dt_txt,"${(item.main.temp - 273).toInt()} C"))
        }

        return returnList
    }
}