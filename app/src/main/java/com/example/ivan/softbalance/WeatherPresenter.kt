package com.example.ivan.softbalance

import com.example.ivan.softbalance.R.id.list
import com.example.ivan.softbalance.model.Response
import com.example.ivan.softbalance.model.Response.X
import com.example.ivan.softbalance.model.WeatherItem
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import rx.Observer

class WeatherPresenter: MvpBasePresenter<WeatherView>(),Observer<Response> {

    val parser:Parser = Parser()

    override fun onError(e: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNext(t: Response?) {
        ifViewAttached {
            it.showNewData(parser.parse(t!!))
        }
    }

    override fun onCompleted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val interactor:WeatherInteractor = WeatherInteractor()

    fun search(city:String){
        if(city.length>3)
            interactor.search(city)
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